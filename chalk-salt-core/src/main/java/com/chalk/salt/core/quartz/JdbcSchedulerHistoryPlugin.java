package com.chalk.salt.core.quartz;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.TriggerListener;
import org.quartz.spi.ClassLoadHelper;
import org.quartz.spi.SchedulerPlugin;
import org.quartz.utils.DBConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This plugin will record a row in a database table for each event (methods) in
 * SchedulerPlugin and TriggerListener. The database table must setup with the
 * following fields, and {@link #insertSql} must be populated correctly with
 * binding parameters.
 * 
 * <p>
 * You must also set the {@link #dataSourceName} name in the quartz.properties.
 * It can be the same data source you setup for the JdbcJobStore configuration
 * (see Quartz doc.)
 * 
 * Here is an example of how to create the history table on MySQL.
 * 
 * <pre>
 * CREATE TABLE qrtz_scheduler_history (
 *   host_ip VARCHAR(15) NOT NULL,
 *   host_name VARCHAR(256) NOT NULL,
 *   scheduler_name VARCHAR(256) NOT NULL,
 *   event_type VARCHAR(128) NOT NULL,
 *   event_name VARCHAR(128) NOT NULL,
 *   event_time TIMESTAMP NOT NULL,
 *   info1 VARCHAR(256) NULL,
 *   info2 VARCHAR(256) NULL,
 *   info3 VARCHAR(256) NULL,
 *   info4 VARCHAR(256) NULL,
 *   info5 VARCHAR(256) NULL,
 *   INDEX(host_ip, host_name, event_type,event_name,event_time)
 * )
 * </pre>
 * 
 * And here is an example of how to create the history table on Oracle.
 * 
 * <pre>
 * CREATE TABLE "QRTZ_SCHEDULER_HISTORY"
 * (
 * "HOST_IP" VARCHAR2(120 BYTE) NOT NULL,
 * "HOST_NAME" VARCHAR2(200 BYTE) NOT NULL,
 * "SCHEDULER_NAME" VARCHAR2(250 BYTE) NOT NULL,
 * "EVENT_TYPE" VARCHAR2(250 BYTE) NOT NULL,
 * "EVENT_NAME" VARCHAR2(250 BYTE) NOT NULL,
 * "EVENT_TIME" TIMESTAMP NOT NULL,
 * "INFO1" VARCHAR2(250 BYTE),
 * "INFO2" VARCHAR2(250 BYTE),
 * "INFO3" VARCHAR2(250 BYTE),
 * "INFO4" VARCHAR2(250 BYTE),
 * "INFO5" VARCHAR2(250 BYTE)
 * );
 * CREATE INDEX QRTZ_SCHEDULER_HISTORY_INDEX on QRTZ_SCHEDULER_HISTORY(HOST_IP, HOST_NAME, EVENT_TYPE,EVENT_NAME,EVENT_TIME);
 * </pre>
 * 
 * <p>
 * Here is an example of how you configure this plugin in
 * <code>quartz.properties</code> file.
 * 
 * <pre>
 * # Jdbc Scheduler History Plugin
 * org.quartz.plugin.MyJobHistoryPlugin.class = myschedule.quartz.extra.JdbcSchedulerHistoryPlugin
 * org.quartz.plugin.MyJobHistoryPlugin.insertSql = INSERT INTO qrtz_scheduler_history VALUES(?,?,?,?,?,?,?,?,?,?,?)
 * org.quartz.plugin.MyJobHistoryPlugin.querySql = SELECT * FROM qrtz_scheduler_history ORDER BY event_time DESC
 * org.quartz.plugin.MyJobHistoryPlugin.deleteSql = DELETE qrtz_scheduler_history WHERE event_time < ?
 * org.quartz.plugin.MyJobHistoryPlugin.deleteIntervalInSecs = 604800
 * org.quartz.plugin.MyJobHistoryPlugin.dataSourceName = quartzDataSource
 * org.quartz.plugin.MyJobHistoryPlugin.schedulerContextKey = JdbcSchedulerHistoryPlugin.Instance
 * 
 * # JobStore: JDBC jobStoreTX
 * org.quartz.dataSource.quartzDataSource.driver = com.mysql.jdbc.Driver
 * org.quartz.dataSource.quartzDataSource.URL = jdbc:mysql://localhost:3306/quartz2
 * org.quartz.dataSource.quartzDataSource.user = quartz2
 * org.quartz.dataSource.quartzDataSource.password = quartz2123
 * org.quartz.dataSource.quartzDataSource.maxConnections = 9
 * </pre>
 * 
 * <p>
 * The <code>event_type</code> can be either <code>SchedulerListener</code> or
 * <code>TriggerListener</code>, and <code>event_name</code> will contain the
 * method name that it was invoked. Note that not all methods are recorded! We
 * only recorded some that we think they are important to track on general
 * purpose.
 * 
 * <p>
 * For <code>SchedulerListener</code>, we are recording these methods:
 * jobScheduled, jobUnscheduled, triggerPaused, triggersPaused, triggerResumed,
 * triggersResumed, schedulerError, schedulerInStandbyMode, schedulerStarted,
 * and schedulerShutdown.
 * 
 * <p>
 * For <code>TriggerListener</code>, we are recording these methods:
 * triggerFired, triggerComplete and triggerMisfired.
 * 
 * <p>
 * If <code>event_type</code> is <code>TriggerListener</code> then info1 =
 * trigger key, info2 = job key, info3 = [FireInstanceId], info4 = [fireTime],
 * info5 = [CompletedExecutionInstruction].
 * 
 * 
 */
public class JdbcSchedulerHistoryPlugin implements SchedulerPlugin {

	/** The Constant DEFAULT_SCHEDULER_CONTEXT_KEY. */
	public static final String DEFAULT_SCHEDULER_CONTEXT_KEY = "JdbcSchedulerHistoryPlugin.Instance";

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory
			.getLogger(JdbcSchedulerHistoryPlugin.class);

	/** The name. */
	private String name;

	/** The scheduler. */
	private Scheduler scheduler;

	/** The local ip. */
	private String localIp;

	/** The local host. */
	private String localHost;

	/** The scheduler name and id. */
	private String schedulerNameAndId;

	/** The data source name. */
	private String dataSourceName;

	/** The insert sql. */
	private String insertSql;

	/** The query sql. */
	private String querySql;

	/** The delete sql. */
	private String deleteSql;

	/** The scheduler context key. */
	private String schedulerContextKey;

	/** The delete interval in secs. */
	private long deleteIntervalInSecs;

	/** The column sql types. */
	private int[] columnSqlTypes;

	/**
	 * Sets the scheduler context key.
	 * 
	 * @param schedulerContextKey
	 *            the new scheduler context key
	 */
	public void setSchedulerContextKey(final String schedulerContextKey) {
		this.schedulerContextKey = schedulerContextKey;
	}

	/**
	 * Sets the data source name.
	 * 
	 * @param dataSourceName
	 *            the new data source name
	 */
	public void setDataSourceName(final String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	/**
	 * Sets the query sql.
	 * 
	 * @param querySql
	 *            the new query sql
	 */
	public void setQuerySql(final String querySql) {
		this.querySql = querySql;
	}

	/**
	 * Sets the insert sql.
	 * 
	 * @param insertSql
	 *            the new insert sql
	 */
	public void setInsertSql(final String insertSql) {
		this.insertSql = insertSql;
	}

	/**
	 * Sets the delete sql.
	 * 
	 * @param deleteSql
	 *            the new delete sql
	 */
	public void setDeleteSql(final String deleteSql) {
		this.deleteSql = deleteSql;
	}

	/**
	 * Sets the delete interval in secs.
	 * 
	 * @param deleteIntervalInSecs
	 *            the new delete interval in secs
	 */
	public void setDeleteIntervalInSecs(final long deleteIntervalInSecs) {
		this.deleteIntervalInSecs = deleteIntervalInSecs;
	}

	/**
	 * Gets the delete interval in secs.
	 * 
	 * @return the delete interval in secs
	 */
	public long getDeleteIntervalInSecs() {
		return deleteIntervalInSecs;
	}

	/**
	 * Gets the job history data.
	 * 
	 * @return the job history data
	 */
	public List<List<Object>> getJobHistoryData() {
		final List<List<Object>> result = new ArrayList<List<Object>>();
		withConn(new ConnAction() {
			@Override
			public void onConn(final Connection conn) throws SQLException {
				final Statement stmt = conn.createStatement();
				final ResultSet rs = stmt.executeQuery(querySql);
				final int colCount = rs.getMetaData().getColumnCount();
				while (rs.next()) {
					final List<Object> row = new ArrayList<Object>();
					for (int i = 1; i <= colCount; i++) {
						row.add(rs.getObject(i));
					}
					result.add(row);
				}
			}
		});
		return result;
	}

	/**
	 * Delete job history.
	 * 
	 * @param olderThanDate
	 *            the older than date
	 * @return the int
	 */
	public int deleteJobHistory(final Date olderThanDate) {
		logger.debug("Delete SQL: {}", deleteSql);
		final List<Integer> result = new ArrayList<Integer>();
		withConn(new ConnAction() {
			@Override
			public void onConn(final Connection conn) throws SQLException {
				final PreparedStatement stmt = conn.prepareStatement(deleteSql);
				stmt.setObject(1,
						new java.sql.Timestamp(olderThanDate.getTime()));
				final int count = stmt.executeUpdate();
				logger.info("History record deleted: {}", count);
				stmt.close();
				result.add(count);
			}
		});
		return result.get(0);
	}

	/**
	 * Insert history.
	 * 
	 * @param sql
	 *            the sql
	 * @param params
	 *            the params
	 */
	private void insertHistory(final String sql, final Object[] params) {
		logger.debug("Insert SQL: {}", sql);
		withConn(new ConnAction() {
			@Override
			public void onConn(final Connection conn) throws SQLException {
				final PreparedStatement stmt = conn.prepareStatement(insertSql);
				if (columnSqlTypes != null) {
					for (int i = 1; i <= params.length; i++) {
						Object param = params[i - 1];
						if (param instanceof Date) {
							final long time = ((Date) param).getTime();
							param = new java.sql.Timestamp(time);
						}
						final int type = columnSqlTypes[i - 1];
						logger.debug("Binding param[{}]: {}, type={}",
								new Object[] { i, param, type });
						stmt.setObject(i, param, type);
					}
				} else {
					for (int i = 1; i <= params.length; i++) {
						final Object param = params[i - 1];
						logger.debug("Binding param[{}]: {}", i, param);
						stmt.setObject(i, param);
					}
				}
				final int result = stmt.executeUpdate();
				logger.info("History record inserted: {}", result);
				stmt.close();
			}
		});
	}

	/**
	 * With conn.
	 * 
	 * @param action
	 *            the action
	 */
	private void withConn(final ConnAction action) {
		Connection conn = null;
		try {
			conn = DBConnectionManager.getInstance().getConnection(
					dataSourceName);
			action.onConn(conn);
		} catch (final SQLException sqle) {
			throw new QuartzRuntimeException(
					"Failed to execute DB connection action.", sqle);
		} finally {
			if (conn != null) {
				try {
					conn.close();
					conn = null;
				} catch (final SQLException sqle) {
					throw new QuartzRuntimeException(
							"Failed to close DB connection.", sqle);
				}
			}
		}
	}

	/**
	 * The Interface ConnAction.
	 */
	private static interface ConnAction {

		/**
		 * On conn.
		 * 
		 * @param conn
		 *            the conn
		 * @throws SQLException
		 *             the sQL exception
		 */
		void onConn(Connection conn) throws SQLException;
	}

	/**
	 * Retrieve local host.
	 * 
	 * @return the string
	 */
	private String retrieveLocalHost() {
		try {
			final InetAddress localHost = InetAddress.getLocalHost();
			return localHost.getHostName();
		} catch (final UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Retrieve local ip.
	 * 
	 * @return the string
	 */
	private String retrieveLocalIp() {
		try {
			final InetAddress localHost = InetAddress.getLocalHost();
			return localHost.getHostAddress();
		} catch (final UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Retrieve scheduler name and id.
	 * 
	 * @return the string
	 */
	private String retrieveSchedulerNameAndId() {
		try {
			return scheduler.getSchedulerName() + "_$_"
					+ scheduler.getSchedulerInstanceId();
		} catch (final SchedulerException e) {
			throw new QuartzRuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.quartz.spi.SchedulerPlugin#start()
	 */
	@Override
	public void start() {
		logger.info(name + " has started.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.quartz.spi.SchedulerPlugin#shutdown()
	 */
	@Override
	public void shutdown() {
		logger.info(name + " has shutdown.");
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Listeners
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * The listener interface for receiving historyScheduler events. The class
	 * that is interested in processing a historyScheduler event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's
	 * <code>addHistorySchedulerListener<code> method. When
	 * the historyScheduler event occurs, that object's appropriate
	 * method is invoked.
	 * 
	 * @see HistorySchedulerEvent
	 */
	private class HistorySchedulerListener implements SchedulerListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.SchedulerListener#jobScheduled(org.quartz.Trigger)
		 */
		@Override
		public void jobScheduled(final Trigger trigger) {
			final Object[] params = new Object[] { localIp, localHost,
					schedulerNameAndId, "SchedulerListener", "jobScheduled",
					new Date(), trigger.getKey().toString(),
					trigger.getJobKey().toString(), null, null, null };
			insertHistory(insertSql, params);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.quartz.SchedulerListener#jobUnscheduled(org.quartz.TriggerKey)
		 */
		@Override
		public void jobUnscheduled(final TriggerKey triggerKey) {
			final Object[] params = new Object[] { localIp, localHost,
					schedulerNameAndId, "SchedulerListener", "jobUnscheduled",
					new Date(), triggerKey.toString(), null, null, null, null };
			insertHistory(insertSql, params);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.quartz.SchedulerListener#triggerPaused(org.quartz.TriggerKey)
		 */
		@Override
		public void triggerPaused(final TriggerKey triggerKey) {
			final Object[] params = new Object[] { localIp, localHost,
					schedulerNameAndId, "SchedulerListener", "triggerPaused",
					new Date(), triggerKey.toString(), null, null, null, null };
			insertHistory(insertSql, params);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.SchedulerListener#triggersPaused(java.lang.String)
		 */
		@Override
		public void triggersPaused(final String triggerGroup) {
			final Object[] params = new Object[] { localIp, localHost,
					schedulerNameAndId, "SchedulerListener", "triggersPaused",
					new Date(), triggerGroup, null, null, null, null };
			insertHistory(insertSql, params);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.quartz.SchedulerListener#triggerResumed(org.quartz.TriggerKey)
		 */
		@Override
		public void triggerResumed(final TriggerKey triggerKey) {
			final Object[] params = new Object[] { localIp, localHost,
					schedulerNameAndId, "SchedulerListener", "triggerResumed",
					new Date(), triggerKey.toString(), null, null, null, null };
			insertHistory(insertSql, params);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.SchedulerListener#triggersResumed(java.lang.String)
		 */
		@Override
		public void triggersResumed(final String triggerGroup) {
			final Object[] params = new Object[] { localIp, localHost,
					schedulerNameAndId, "SchedulerListener", "triggersResumed",
					new Date(), triggerGroup, null, null, null, null };
			insertHistory(insertSql, params);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.SchedulerListener#schedulerError(java.lang.String,
		 * org.quartz.SchedulerException)
		 */
		@Override
		public void schedulerError(final String msg,
				final SchedulerException cause) {
			final Object[] params = new Object[] { localIp, localHost,
					schedulerNameAndId, "SchedulerListener", "schedulerError",
					new Date(), msg, cause.getClass().getName(), null, null,
					null };
			insertHistory(insertSql, params);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.SchedulerListener#schedulerInStandbyMode()
		 */
		@Override
		public void schedulerInStandbyMode() {
			final Object[] params = new Object[] { localIp, localHost,
					schedulerNameAndId, "SchedulerListener",
					"schedulerInStandbyMode", new Date(), null, null, null,
					null, null };
			insertHistory(insertSql, params);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.SchedulerListener#schedulerStarted()
		 */
		@Override
		public void schedulerStarted() {
			final Object[] params = new Object[] { localIp, localHost,
					schedulerNameAndId, "SchedulerListener",
					"schedulerStarted", new Date(), null, null, null, null,
					null };
			insertHistory(insertSql, params);

			// Auto add a job to delete job history if configured to do so
			if (deleteIntervalInSecs > 0) {
				try {
					final String jobName = "JobHistoryRemovalJob";
					if (scheduler.checkExists(TriggerKey.triggerKey(jobName))) {
						scheduler.unscheduleJob(TriggerKey.triggerKey(jobName));
						logger.info("The JobHistoryRemovalJob already exist. Removed it from scheduler first.");
					}
					final JobDetail job = JobBuilder
							.newJob(JobHistoryRemovalJob.class)
							.withIdentity(jobName)
							.usingJobData(JobHistoryRemovalJob.PLUGIN_KEY_NAME,
									schedulerContextKey).build();
					final Trigger trigger = TriggerBuilder
							.newTrigger()
							.withIdentity(jobName)
							.withSchedule(
									SimpleScheduleBuilder
											.repeatSecondlyForever(
													(int) deleteIntervalInSecs)
											.withMisfireHandlingInstructionNextWithRemainingCount())
							.startAt(
									new Date(System.currentTimeMillis()
											+ (deleteIntervalInSecs * 1000)))
							.build();
					scheduler.scheduleJob(job, trigger);
					logger.info(
							"Added JobHistoryRemovalJob that runs every {} secs.",
							deleteIntervalInSecs);
				} catch (final SchedulerException e) {
					logger.error("Failed to insert JobHistoryRemovalJob.", e);
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.SchedulerListener#schedulerShutdown()
		 */
		@Override
		public void schedulerShutdown() {
			// TODO: We can not insert SQL data here yet. See QTZ-257.
			// For now, the workaround is use schedulerShuttingdown(), which
			// called before all pending jobs are
			// completed.
			// Object[] params = new Object[] {
			// localIp,
			// localHost,
			// schedulerNameAndId,
			// "SchedulerListener",
			// "schedulerShutdown",
			// new Date(),
			// null,
			// null,
			// null,
			// null,
			// null
			// };
			// insertHistory(insertSql, params);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.SchedulerListener#schedulerShuttingdown()
		 */
		@Override
		public void schedulerShuttingdown() {
			final Object[] params = new Object[] { localIp, localHost,
					schedulerNameAndId, "SchedulerListener",
					"schedulerShuttingdown", new Date(), null, null, null,
					null, null };
			insertHistory(insertSql, params);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.SchedulerListener#schedulingDataCleared()
		 */
		@Override
		public void schedulingDataCleared() {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.SchedulerListener#jobAdded(org.quartz.JobDetail)
		 */
		@Override
		public void jobAdded(final JobDetail jobDetail) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.SchedulerListener#jobDeleted(org.quartz.JobKey)
		 */
		@Override
		public void jobDeleted(final JobKey jobKey) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.SchedulerListener#jobPaused(org.quartz.JobKey)
		 */
		@Override
		public void jobPaused(final JobKey jobKey) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.SchedulerListener#jobsPaused(java.lang.String)
		 */
		@Override
		public void jobsPaused(final String jobGroup) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.SchedulerListener#jobResumed(org.quartz.JobKey)
		 */
		@Override
		public void jobResumed(final JobKey jobKey) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.SchedulerListener#jobsResumed(java.lang.String)
		 */
		@Override
		public void jobsResumed(final String jobGroup) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.quartz.SchedulerListener#triggerFinalized(org.quartz.Trigger)
		 */
		@Override
		public void triggerFinalized(final Trigger trigger) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.SchedulerListener#schedulerStarting()
		 */
		@Override
		public void schedulerStarting() {

		}
	}

	/**
	 * The listener interface for receiving historyTrigger events. The class
	 * that is interested in processing a historyTrigger event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's
	 * <code>addHistoryTriggerListener<code> method. When
	 * the historyTrigger event occurs, that object's appropriate
	 * method is invoked.
	 * 
	 * @see HistoryTriggerEvent
	 */
	private class HistoryTriggerListener implements TriggerListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.TriggerListener#getName()
		 */
		@Override
		public String getName() {
			return name;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.TriggerListener#triggerFired(org.quartz.Trigger,
		 * org.quartz.JobExecutionContext)
		 */
		@Override
		public void triggerFired(final Trigger trigger,
				final JobExecutionContext context) {
			final Object[] params = new Object[] { localIp, localHost,
					schedulerNameAndId, "TriggerListener", "triggerFired",
					new Date(), trigger.getKey().toString(),
					trigger.getJobKey().toString(),
					context.getFireInstanceId(), context.getFireTime(), null };

			insertHistory(insertSql, params);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.TriggerListener#vetoJobExecution(org.quartz.Trigger,
		 * org.quartz.JobExecutionContext)
		 */
		@Override
		public boolean vetoJobExecution(final Trigger trigger,
				final JobExecutionContext context) {
			// Do nothing.
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.TriggerListener#triggerMisfired(org.quartz.Trigger)
		 */
		@Override
		public void triggerMisfired(final Trigger trigger) {
			final Object[] params = new Object[] { localIp, localHost,
					schedulerNameAndId, "TriggerListener", "triggerMisfired",
					new Date(), trigger.getKey().toString(),
					trigger.getJobKey().toString(), null, null, null };

			insertHistory(insertSql, params);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.TriggerListener#triggerComplete(org.quartz.Trigger,
		 * org.quartz.JobExecutionContext,
		 * org.quartz.Trigger.CompletedExecutionInstruction)
		 */
		@Override
		public void triggerComplete(final Trigger trigger,
				final JobExecutionContext context,
				final CompletedExecutionInstruction triggerInstructionCode) {
			final Object[] params = new Object[] { localIp, localHost,
					schedulerNameAndId, "TriggerListener", "triggerComplete",
					new Date(), trigger.getKey().toString(),
					trigger.getJobKey().toString(),
					context.getFireInstanceId(), context.getFireTime(),
					triggerInstructionCode.toString() };

			insertHistory(insertSql, params);
		}
	}

	/**
	 * The Class JobHistoryRemovalJob.
	 */
	public static class JobHistoryRemovalJob implements Job {

		/** The Constant PLUGIN_KEY_NAME. */
		public static final String PLUGIN_KEY_NAME = "JdbcSchedulerHistoryPluginKey";

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
		 */
		@Override
		public void execute(final JobExecutionContext context)
				throws JobExecutionException {
			try {
				final Scheduler scheduler = context.getScheduler();
				final String pluginKey = context.getMergedJobDataMap()
						.getString(PLUGIN_KEY_NAME);
				final JdbcSchedulerHistoryPlugin plugin = (JdbcSchedulerHistoryPlugin) scheduler
						.getContext().get(pluginKey);
				final long deleteIntervalInSecs = plugin
						.getDeleteIntervalInSecs();
				final Date olderThanDate = new Date(System.currentTimeMillis()
						- (deleteIntervalInSecs * 1000));
				final int result = plugin.deleteJobHistory(olderThanDate);
				logger.info(
						"{} job history records were deleted with date older than {}.",
						result, olderThanDate);
			} catch (final SchedulerException e) {
				throw new JobExecutionException(
						"Failed to run JobHistoryRemovalJob.", e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.quartz.spi.SchedulerPlugin#initialize(java.lang.String,
	 * org.quartz.Scheduler, org.quartz.spi.ClassLoadHelper)
	 */
	@Override
	public void initialize(final String name, final Scheduler scheduler,
			final ClassLoadHelper classLoadHelper) throws SchedulerException {
		this.name = name;
		this.scheduler = scheduler;
		this.localIp = retrieveLocalIp();
		this.localHost = retrieveLocalHost();
		this.schedulerNameAndId = retrieveSchedulerNameAndId();

		// Register listeners
		scheduler.getListenerManager().addTriggerListener(
				new HistoryTriggerListener());
		scheduler.getListenerManager().addSchedulerListener(
				new HistorySchedulerListener());

		// Store this plugin instance into scheduler context map
		if (schedulerContextKey == null) {
			schedulerContextKey = DEFAULT_SCHEDULER_CONTEXT_KEY;
		}
		scheduler.getContext().put(schedulerContextKey, this);
		logger.info("Added plugin instance {} to scheduler context key: {}",
				this, schedulerContextKey);

		// Extract and find all the SQL types for table columns.
		withConn(new ConnAction() {
			@Override
			public void onConn(final Connection conn) throws SQLException {
				final PreparedStatement stmt = conn.prepareStatement(querySql);
				final ResultSetMetaData metaData = stmt.getMetaData();
				if (metaData != null) {
					final int size = metaData.getColumnCount();
					columnSqlTypes = new int[size];
					for (int i = 1; i <= size; i++) {
						final int type = metaData.getColumnType(i);
						final String typeName = metaData.getColumnTypeName(i);
						final String name = metaData.getColumnName(i);
						logger.debug(
								"History table SQL column {}, type={}, typeName={}",
								new Object[] { name, type, typeName });
						columnSqlTypes[i - 1] = type;
					}
				}
				stmt.close();
			}
		});
	}
}