package com.chalk.dust.dao.system.lookup;

import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import com.chalk.salt.common.dto.ChalkSaltConstants;
import com.chalk.salt.common.dto.UserClassDto;
import com.chalk.salt.dao.sql2o.connection.factory.ConnectionFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class SystemLookupDaoImpl.
 */
public class SystemLookupDaoImpl implements SystemLookupDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chalk.dust.dao.system.lookup.SystemLookupDao#getUserClassesList()
	 */
	@Override
	public List<UserClassDto> getUserClassesList() throws Exception {
		final String sqlQuery = "SELECT class_id,class_name FROM cst_class_type ORDER BY class_id";
		final Sql2o dataSource = ConnectionFactory
				.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
		try (final Connection connection = dataSource.open()) {
			final Query query = connection.createQuery(sqlQuery);
			query.addColumnMapping("class_id", "classId");
			query.addColumnMapping("class_name", "className");
			return query.executeAndFetch(UserClassDto.class);
		}
	}

}
