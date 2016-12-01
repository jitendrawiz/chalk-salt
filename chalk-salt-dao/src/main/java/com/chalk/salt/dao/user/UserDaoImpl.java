package com.chalk.salt.dao.user;

import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import org.sql2o.data.Row;
import org.sql2o.data.Table;

import com.chalk.salt.common.dto.AcademicInfoDto;
import com.chalk.salt.common.dto.ChalkSaltConstants;
import com.chalk.salt.common.dto.DiscussionTopicRequestDto;
import com.chalk.salt.common.dto.GuestUserDto;
import com.chalk.salt.common.dto.ParentsInfoDto;
import com.chalk.salt.common.dto.StudentAchievementDto;
import com.chalk.salt.common.dto.SubjectDto;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.dao.sql2o.connection.factory.ConnectionFactory;

/**
 * The Class UserDaoImpl.
 */
public class UserDaoImpl implements UserDao {
    
    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.UserDao#isUserExist(java.lang.String)
     */
    @Override
    public boolean isUserExist(final String userName) throws Exception {
        final String sqlQuery = "SELECT `cst_logins`.`username` AS userName  "
            + " FROM cst_logins AS cst_logins "
            + "WHERE ((cst_logins.username = :userName))";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("userName", userName);
            final Table table = query.executeAndFetchTable();
            final List<Row> rows = table.rows();
            if (rows != null && !rows.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.UserDao#saveLoginDetails(java.lang.String, java.lang.String)
     */
    @Override
    public Long saveLoginDetails(final String username, final String hashedPassword) throws Exception {
        final String sqlQuery = "INSERT  INTO cst_logins (`username`,`password`,`active`) "
            + "VALUES(:username, :password, :active)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("username", username);
            query.addParameter("password", hashedPassword);
            query.addParameter("active", 1);
            return (Long) query.executeUpdate().getKey();
        }
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.UserDao#saveUserDetails(com.chalk.salt.common.dto.UserDto)
     */
    @Override
    public boolean saveUserDetails(final UserDto userDetail) throws Exception {

        final String sqlQuery = "INSERT INTO cst_users(`user_id`, `first_name`, `middle_name`, `last_name`, `contact_id`, `secur_uuid`,class_id,academic_id,parents_id)"
        		+ "VALUES(:userId, :firstName, :middleName, :lastName, :contactId, :securUuid,:studentClass,:academicId,:parentsId)";

        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);

            query.addParameter("securUuid", userDetail.getSecurUuid());
            query.addParameter("firstName", userDetail.getFirstName());
            query.addParameter("middleName", userDetail.getMiddleName());
            query.addParameter("lastName", userDetail.getLastName());
            query.addParameter("userId", userDetail.getUserId());
            query.addParameter("contactId", userDetail.getContactId());
            query.addParameter("studentClass", userDetail.getStudentClass());
            query.addParameter("academicId", userDetail.getAcademicId());
            query.addParameter("parentsId", userDetail.getParentsId());
            query.executeUpdate();
        }
        return true;
    }
    
  /* (non-Javadoc)
   * @see com.chalk.salt.dao.user.UserDao#saveContactDetails(com.chalk.salt.common.dto.UserDto)
   */
  /*  
    public Long getIdByUuid(final String tableName, final String idFieldName, final String uuidFieldName, final String uuid) throws Exception {

        final Long id;
        final String sqlQuery = "SELECT " + idFieldName + " FROM " + tableName + " WHERE " + uuidFieldName + "=:uuid";

        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("uuid", uuid);
            id = query.executeAndFetchFirst(Long.class);
        }
        return id;
    }
*/
	@Override
	public Long saveContactDetails(final UserDto userDetail) throws Exception {
		
		final String sqlQuery = "INSERT INTO cst_contacts(`address`, `city`, `state`, `country`, `pincode`, `mobile`, `landline`, `email`)"
				+ " VALUES(:address, :city, :state, :country, :pincode, :mobile, :landline, :email)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("address", userDetail.getAddress());
            query.addParameter("city", userDetail.getCity());
            query.addParameter("state", userDetail.getState());
            query.addParameter("country", userDetail.getCountry());
            query.addParameter("pincode", userDetail.getPincode());
            query.addParameter("mobile", userDetail.getMobile());
            query.addParameter("landline", userDetail.getLandline());
            query.addParameter("email", userDetail.getEmail());
            return (Long) query.executeUpdate().getKey();
        }
	}
	

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.UserDao#getUserInfo(java.lang.String)
     */
    @Override
    public UserDto getUserInfo(final String securUuid) throws Exception {

        UserDto user = null;

        final String sqlQuery = "SELECT first_name as firstName, middle_name as middleName, last_name as lastName, address, city, state, "
    		+ "country, pincode, email, username, mobile, landline, secur_uuid as securUuid ,corsAddress as correspondenceAddress,profile_photo as profilePhoto,cst_logins.user_id as userId "
    		+ "FROM cst_users "
    		+ "JOIN cst_logins ON cst_logins.user_id = cst_users.user_id "
    		+ "JOIN cst_contacts ON cst_contacts.id = cst_users.contact_id "
    		+ "WHERE secur_uuid =:securUuid AND active =1";
           
    	final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
    	try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securUuid", securUuid);
            user = query.executeAndFetchFirst(UserDto.class);
        }
        return user;
    }

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#getUserSubjects(java.lang.String)
	 */
	@Override
	public List<SubjectDto> getUserSubjects(final String securUuid) throws Exception {
		 final String sqlQuery = "SELECT cst_class_subjects.subject_id,cst_class_subjects.subject_name FROM cst_users "
		 		+ " JOIN cst_class_type ON cst_class_type.class_id=cst_users.class_id "
		 		+ " JOIN cst_class_subject_mapping ON cst_class_subject_mapping.class_id=cst_class_type.class_id "
		 		+ " JOIN cst_class_subjects ON cst_class_subjects.subject_id=cst_class_subject_mapping.subject_id "
		 		+ " WHERE cst_users.secur_uuid=:securUuid";
		        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
		        try (final Connection connection = dataSource.open()) {
		            final Query query = connection.createQuery(sqlQuery);
		            query.addParameter("securUuid", securUuid);
		            query.addColumnMapping("subject_id", "subjectId");
		            query.addColumnMapping("subject_name", "subjectName");
		            return query.executeAndFetch(SubjectDto.class);
		        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#getAcademicInfo(java.lang.String)
	 */
	@Override
	public AcademicInfoDto getAcademicInfo(final String securUuid) throws Exception {
		
		AcademicInfoDto academicInfo = null;

        final String sqlQuery = "SELECT class_name as studentClassName, `student_class_id` as studentClassId, `percentage`, "
    		+ "`previous_school` as previousSchool FROM cst_users "
    		+ "JOIN cst_class_type ON cst_class_type.class_id = cst_users.class_id "
    		+ "JOIN cst_academic_details ON cst_academic_details.id = cst_users.academic_id "
    		+ "WHERE secur_uuid =:securUuid";
           
    	final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
    	try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securUuid", securUuid);
            academicInfo = query.executeAndFetchFirst(AcademicInfoDto.class);
        }
        return academicInfo;
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#getParentsInfo(java.lang.String)
	 */
	@Override
	public ParentsInfoDto getParentsInfo(final String securUuid) throws Exception {
		
		ParentsInfoDto parentsInfo = null;

        final String sqlQuery = "SELECT `father_name` as fatherName, `mother_name` as motherName, `father_email` as fatherEmail, `mother_email` as motherEmail, "
    		+ "`father_mobile` as fatherMobile, `mother_mobile` as motherMobile, `father_office_address` as fatherOfficeAddress, "
    		+ "`mother_office_address` as motherOfficeAddress, `father_occupation` as fatherOccupation, "
    		+ "`mother_occupation` as motherOccupation FROM cst_users "
    		+ "JOIN cst_parents ON cst_parents.id = cst_users.parents_id "
    		+ "WHERE secur_uuid =:securUuid";
           
    	final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
    	try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securUuid", securUuid);
            parentsInfo = query.executeAndFetchFirst(ParentsInfoDto.class);
        }
        return parentsInfo;
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#updateAcademicDetails(com.chalk.salt.common.dto.AcademicInfoDto)
	 */
	@Override
	public void updateAcademicDetails(final AcademicInfoDto academicInfo)
			throws Exception {
		final String sqlQuery = "UPDATE cst_academic_details "
				+ " SET percentage=:percentage,"
				+ " previous_school=:schooling "
				+ " WHERE id=:academic_id";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("percentage", academicInfo.getPercentage());
            query.addParameter("schooling", academicInfo.getPreviousSchool());
            query.addParameter("academic_id", academicInfo.getAcademicInfoId());           
            query.executeUpdate();
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#updateParentsDetails(com.chalk.salt.common.dto.ParentsInfoDto)
	 */
	@Override
	public void updateParentsDetails(final ParentsInfoDto parentsInfo)
			throws Exception {
		final String sqlQuery = "Update cst_parents set father_name=:fatherName,"
				+ " mother_name=:motherName, father_email=:fatherEmail,"
				+ " mother_email=:motherEmail, father_mobile=:fatherMobile,mother_mobile=:motherMobile,"
				+ " father_office_address=:fatherOfficeAddress,"
				+ " mother_office_address=:motherOfficeAddress,"
				+ " father_occupation=:fatherOccupation,"
				+ " mother_occupation=:motherOccupation"
				+ " where id=:parentId";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("fatherName", parentsInfo.getFatherName());
            query.addParameter("motherName", parentsInfo.getMotherName());
            query.addParameter("fatherEmail", parentsInfo.getFatherEmail());   
            query.addParameter("motherEmail", parentsInfo.getMotherEmail());
            query.addParameter("fatherMobile", parentsInfo.getFatherMobile());
            query.addParameter("motherMobile", parentsInfo.getMotherMobile());   
            query.addParameter("fatherOfficeAddress", parentsInfo.getFatherOfficeAddress());
            query.addParameter("motherOfficeAddress", parentsInfo.getMotherOfficeAddress());
            query.addParameter("fatherOccupation", parentsInfo.getFatherOccupation());   
            query.addParameter("motherOccupation", parentsInfo.getMotherOccupation());   
            query.addParameter("parentId", parentsInfo.getParentId()); 
            query.executeUpdate();
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#updateUserDetails(com.chalk.salt.common.dto.UserDto)
	 */
	@Override
	public Boolean updateUserDetails(final UserDto userDetail) throws Exception {
		final String sqlQuery = "UPDATE cst_users SET first_name=:firstName,middle_name=:middleName,last_name=:lastName "
				+ " WHERE secur_uuid=:securUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("firstName", userDetail.getFirstName());
            query.addParameter("middleName", userDetail.getMiddleName());
            query.addParameter("lastName", userDetail.getLastName());
            query.addParameter("securUuid", userDetail.getSecurUuid());
            query.executeUpdate();
            return true;
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#updateContactDetails(com.chalk.salt.common.dto.UserDto)
	 */
	@Override
	public Boolean updateContactDetails(final UserDto userDetail) throws Exception {
		final String sqlQuery = "UPDATE cst_contacts "
				+ " SET address=:address,"
				+ " mobile=:mobile,"
				+ " landline=:landline,"
				+ " email=:email,corsAddress=:corsAddress "
				+ " WHERE id=:contactId";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("address", userDetail.getAddress());
            query.addParameter("mobile", userDetail.getMobile());
            query.addParameter("landline", userDetail.getLandline());
            query.addParameter("email", userDetail.getEmail());
            query.addParameter("corsAddress", userDetail.getCorrespondenceAddress());
            query.addParameter("contactId", userDetail.getContactId());
            query.executeUpdate();
            return true;
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#getUserKeyDetails(java.lang.String)
	 */
	@Override
	public UserDto getUserKeyDetails(final String securUuid) throws Exception {
        final String sqlQuery = " SELECT contact_id,class_id,parents_id,academic_id "
        		+ " FROM cst_users WHERE secur_uuid=:securUuid";
    	final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
    	try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securUuid", securUuid);
            query.addColumnMapping("contact_id", "contactId");
            query.addColumnMapping("parents_id", "parentsId");
            query.addColumnMapping("academic_id", "academicId");
            query.addColumnMapping("class_id", "classId");
            return query.executeAndFetchFirst(UserDto.class);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#changePassword(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean changePassword(final String userName, final String password,
			final String newPassword) throws Exception {
		
		final String sqlQuery = "UPDATE cst_logins "
				+ " SET password=:newPassword"				
				+ " WHERE username=:userName";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
         //   query.addParameter("password", password);
            query.addParameter("newPassword", newPassword);
            query.addParameter("userName", userName);           
            query.executeUpdate();
            return true;
        }        
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#saveAcademicDetails(com.chalk.salt.common.dto.AcademicInfoDto)
	 */
	@Override
	public Long saveAcademicDetails(AcademicInfoDto academicInfo) throws Exception {
		final String sqlQuery = "INSERT into cst_academic_details "
				+ " (percentage, previous_school, student_class_id) VALUES(:percentage,:schooling,:studentClassId)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("percentage", academicInfo.getPercentage());
            query.addParameter("schooling", academicInfo.getPreviousSchool());
            query.addParameter("studentClassId", academicInfo.getStudentClassId());           
            return (Long)query.executeUpdate().getKey();
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#saveParentsDetails(com.chalk.salt.common.dto.ParentsInfoDto)
	 */
	@Override
	public Long saveParentsDetails(ParentsInfoDto parentsInfo) throws Exception {
		final String sqlQuery = "INSERT INTO cst_parents "
				+ " (father_name, mother_name, father_email, mother_email, father_mobile, "
				+ " mother_mobile, father_office_address, mother_office_address, father_occupation, mother_occupation) "
				+ " VALUES(:fatherName, :motherName, :fatherEmail, :motherEmail, :fatherMobile, :motherMobile, "
				+ " :fatherOfficeAddress, :motherOfficeAddress, :fatherOccupation, :motherOccupation)";
				
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("fatherName", parentsInfo.getFatherName());
            query.addParameter("motherName", parentsInfo.getMotherName());
            query.addParameter("fatherEmail", parentsInfo.getFatherEmail());   
            query.addParameter("motherEmail", parentsInfo.getMotherEmail());
            query.addParameter("fatherMobile", parentsInfo.getFatherMobile());
            query.addParameter("motherMobile", parentsInfo.getMotherMobile());   
            query.addParameter("fatherOfficeAddress", parentsInfo.getFatherOfficeAddress());
            query.addParameter("motherOfficeAddress", parentsInfo.getMotherOfficeAddress());
            query.addParameter("fatherOccupation", parentsInfo.getFatherOccupation());   
            query.addParameter("motherOccupation", parentsInfo.getMotherOccupation());   
            return (Long) query.executeUpdate().getKey();
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#getStudents()
	 */
	@Override
	public List<UserDto> getStudents() throws Exception {
		final String sqlQuery = "SELECT cst_users.`first_name` as firstName, cst_users.`middle_name` as middleName,"
				+ "cst_users.`last_name` as lastName, cst_users.`secur_uuid` as securUuid, cst_contacts.`mobile` as mobile,"
				+ "	cst_contacts.`email` as email, class_type.class_name as studentClass FROM `cst_users` AS cst_users "
				+ "	JOIN `cst_contacts` AS cst_contacts ON cst_contacts.id = cst_users.contact_id "
				+ " JOIN cst_class_type AS class_type ON class_type.class_id = cst_users.class_id"
				+ " JOIN cst_logins as cst_logins ON cst_logins.user_id = cst_users.user_id"
				+ " WHERE cst_logins.username<>'admin'";
		
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);   
            return query.executeAndFetch(UserDto.class);
        }
        
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#deleteStudent(java.lang.String)
	 */
	@Override
	public void deleteStudent(String securUuid) throws Exception {
		final String sqlQuery = "DELETE from cst_users where secur_uuid=:securUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("securUuid", securUuid);
            query.executeUpdate();
        }     		
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#deleteContact(java.lang.String)
	 */
	@Override
	public void deleteContact(String securUuid) throws Exception {
		final String sqlQuery = "DELETE contact.* FROM cst_contacts contact "
				+ " JOIN cst_users ON cst_users.contact_id = contact.id "
				+ " WHERE cst_users.secur_uuid =:securUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("securUuid", securUuid);
            query.executeUpdate();
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#deleteParents(java.lang.String)
	 */
	@Override
	public void deleteParents(String securUuid) throws Exception {
		final String sqlQuery = "DELETE parents.* FROM cst_parents parents "
				+ " JOIN cst_users ON cst_users.parents_id = parents.id "
				+ " WHERE cst_users.secur_uuid =:securUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("securUuid", securUuid);
            query.executeUpdate();
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#deleteAcademic(java.lang.String)
	 */
	@Override
	public void deleteAcademic(String securUuid) throws Exception {
		final String sqlQuery = "DELETE academic.* FROM cst_academic_details academic "
				+ " JOIN cst_users ON cst_users.academic_id = academic.id "
				+ " WHERE cst_users.secur_uuid =:securUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("securUuid", securUuid);
            query.executeUpdate();
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#deleteTopicComment(java.lang.String)
	 */
	@Override
	public void deleteTopicComment(String securUuid) throws Exception {
		final String sqlQuery = "DELETE from cst_discussion_topic_comments WHERE user_securUuid =:securUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("securUuid", securUuid);
            query.executeUpdate();
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#deleteLogin(java.lang.String)
	 */
	@Override
	public void deleteLogin(String securUuid) throws Exception {
		final String sqlQuery = "DELETE cst_logins.* FROM cst_logins cst_logins "
				+ " JOIN cst_users ON cst_users.user_id = cst_logins.user_id "
				+ " WHERE cst_users.secur_uuid =:securUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("securUuid", securUuid);
            query.executeUpdate();
        }
		
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#saveTopicRequest(com.chalk.salt.common.dto.DiscussionTopicRequestDto)
	 */
	@Override
	public String saveTopicRequest(DiscussionTopicRequestDto discussionDetails) throws Exception {
		final String sqlQuery = "INSERT INTO cst_topic_requests(`topic_title`, `topic_description`, `secur_uuid`, "
				+ "`subject_id`, `class_id`, `request_date`, `approval_date`, request_securuuid)"
				+ "VALUES (:topicTitle, :topicDescription, :securUuid, :subjectId, :classId, "
				+ ":requestDate, :approvalDate, :requestSecurUuid)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("topicTitle", discussionDetails.getTopicTitle());
            query.addParameter("topicDescription", discussionDetails.getTopicDescription());
            query.addParameter("subjectId", discussionDetails.getSubjectId());
            query.addParameter("classId", discussionDetails.getClassId());
            query.addParameter("securUuid", discussionDetails.getSecurUuid());
            query.addParameter("requestDate", discussionDetails.getRequestDate());
            query.addParameter("approvalDate", discussionDetails.getApprovalDate());
            query.addParameter("requestSecurUuid", discussionDetails.getRequestSecurUuid());
            
            query.executeUpdate();
            return discussionDetails.getRequestSecurUuid();
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#getSystemSettings(java.lang.String)
	 */
	@Override
	public String getSystemSettings(String settingsKey) throws Exception {

		final String sqlQuery = "SELECT `settings_value` AS settingsValue "
				+ "FROM `cst_system_settings` AS systemSettings WHERE systemSettings.settings_key=:settingsKey";
	
		final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
    	try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("settingsKey", settingsKey);
            return query.executeAndFetchFirst(String.class);
    	}
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#getUserIdUsingSecurUuid(java.lang.String)
	 */
	@Override
	public Integer getUserIdUsingSecurUuid(String securUuid) throws Exception {
		final String sqlQuery = "SELECT cst_users.id "
				+ " FROM cst_users "
				+ " WHERE cst_users.secur_uuid=:securUuid";
	
		final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
    	try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securUuid", securUuid);
            return query.executeAndFetchFirst(Integer.class);
    	}
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#updateUserProfilePictureDetails(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateUserProfilePictureDetails(String fileName,
			String securUuid) throws Exception {
		final String sqlQuery = "UPDATE cst_users SET profile_photo=:fileName "
				+ " WHERE secur_uuid=:securUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("fileName", fileName);          
            query.addParameter("securUuid", securUuid);
            query.executeUpdate();
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#getUserProfilePhoto(java.lang.String)
	 */
	@Override
	public String getUserProfilePhoto(String securUuid) throws Exception {
		final String sqlQuery = "SELECT profile_photo from cst_users "
    		+ " WHERE secur_uuid =:securUuid ";
           
    	final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
    	try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securUuid", securUuid);
            return query.executeAndFetchFirst(String.class);
        }
       
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#deleteUserPhotoFromDB(java.lang.String)
	 */
	@Override
	public void deleteUserPhotoFromDB(String securUuid) throws Exception {
		final String sqlQuery = "UPDATE `cst_users` SET `cst_users`.profile_photo=NULL"
				+ " WHERE secur_uuid=:securUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("securUuid", securUuid);
            query.executeUpdate();
        }
		
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#getTopicIdUsingSecurUuid(java.lang.String)
	 */
	@Override
	public Integer getTopicIdUsingSecurUuid(String securUuid) throws Exception {
		final String sqlQuery = "SELECT discussion_topic_id FROM `cst_discussion_topics` "
				+ " WHERE secur_uuid=:securUuid";
	
		final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
    	try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securUuid", securUuid);
            return query.executeAndFetchFirst(Integer.class);
    	}
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#getPreviousTopicImage(java.lang.String)
	 */
	@Override
	public String getPreviousTopicImage(String securUuid) throws Exception {
		final String sqlQuery = "SELECT topic_image from cst_discussion_topics "
	    		+ " WHERE secur_uuid =:securUuid ";
	           
    	final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
    	try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securUuid", securUuid);
            return query.executeAndFetchFirst(String.class);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#updateTopicImageDetails(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateTopicImageDetails(String fileNameToSave, String securUuid)
			throws Exception {
		final String sqlQuery = "UPDATE cst_discussion_topics SET topic_image=:fileName "
				+ " WHERE secur_uuid=:securUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("fileName", fileNameToSave);          
            query.addParameter("securUuid", securUuid);
            query.executeUpdate();
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#getPreviousTopicRequestImage(java.lang.String)
	 */
	@Override
	public String getPreviousTopicRequestImage(String securUuid) throws Exception {
		final String sqlQuery = "SELECT topic_image from cst_topic_requests "
	    		+ " WHERE secur_uuid =:securUuid ";
    	final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
    	try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securUuid", securUuid);
            return query.executeAndFetchFirst(String.class);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#updateTopicRequestImageDetails(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateTopicRequestImageDetails(String fileNameToSave, String securUuid) throws Exception {
		final String sqlQuery = "UPDATE cst_topic_requests SET topic_image=:fileName "
				+ " WHERE request_securuuid=:securUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("fileName", fileNameToSave);          
            query.addParameter("securUuid", securUuid);
            query.executeUpdate();
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#saveGuestUserDetails(com.chalk.salt.common.dto.GuestUserDto)
	 */
	@Override
	public boolean saveGuestUserDetails(GuestUserDto userDetails) throws Exception {
		final String sqlQuery = "INSERT INTO cst_guestusers(`username`,	`email`, `mobileno`, `secur_uuid`,class_id)"
				+ " VALUES(:userName, :email, :mobileNo, :securUuid,:classId)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("userName", userDetails.getUserName());
            query.addParameter("email", userDetails.getEmail());
            query.addParameter("mobileNo", userDetails.getMobileNo());
            query.addParameter("securUuid", userDetails.getSecurUuid());
            query.addParameter("classId", userDetails.getClassId());
            query.executeUpdate();
        }
		return true;
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#checkGuestUserExists(com.chalk.salt.common.dto.GuestUserDto)
	 */
	@Override
	public String checkGuestUserExists(GuestUserDto userDetails) throws Exception {
		final String sqlQuery = "SELECT secur_uuid as securUuid from cst_guestusers WHERE email=:email AND mobileno=:mobileNo limit 1";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("email", userDetails.getEmail());
            query.addParameter("mobileNo", userDetails.getMobileNo());
            return query.executeAndFetchFirst(String.class);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#getGuestUserInfo(java.lang.String)
	 */
	@Override
	public GuestUserDto getGuestUserInfo(String securUuid) throws Exception {
		final String sqlQuery = "SELECT username AS userName,email,mobileno AS mobileNo,"
				+ " class_id AS classId, secur_uuid AS securUuid  FROM `cst_guestusers` WHERE secur_uuid =:securUuid ";
    	final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
    	try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securUuid", securUuid);
            return query.executeAndFetchFirst(GuestUserDto.class);
	}
}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.UserDao#resetPassword(java.lang.Long, java.lang.String)
	 */
	@Override
	public void resetPassword(Long userId, String tempPassword) throws Exception {
		final String sqlQuery = "UPDATE cst_logins SET password=:tempPassword "
				+ " WHERE user_id=:userId";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("tempPassword", tempPassword);          
            query.addParameter("userId", userId);
            query.executeUpdate();
        }
	}

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.UserDao#saveStudentAchievementDetails(com.chalk.salt.common.dto.StudentAchievementDto)
     */
    @Override
    public String saveStudentAchievementDetails(StudentAchievementDto studentAchievementetails) throws Exception
        {
        final String sqlQuery = "INSERT INTO `cst_achievements` (`class_id`, `student_id`, `achievement_description`, "
                + "`file_name`, `modified_date` ,achievement_uuid)"
                + "VALUES(:classId, :studentId, :achvDesc, :fileName, :modifiedDate,:achvUuid)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("classId", studentAchievementetails.getClassId());
            query.addParameter("studentId", studentAchievementetails.getStudentId());
            query.addParameter("achvDesc", studentAchievementetails.getAchievementDesc());
            query.addParameter("fileName", studentAchievementetails.getFileName());
            query.addParameter("modifiedDate", studentAchievementetails.getModified_date());
            query.addParameter("achvUuid", studentAchievementetails.getAchievementUuid());
            query.executeUpdate();
            return studentAchievementetails.getAchievementUuid();
        }        }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.UserDao#getUserIdUsingAchievementUuid(java.lang.String)
     */
    @Override
    public String getUserIdUsingAchievementUuid(String achievementUuid) throws Exception
        {
        final String sqlQuery = "SELECT student_id AS userId FROM  `cst_achievements` WHERE achievement_uuid=:achievementUuid";
    
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("achievementUuid", achievementUuid);
            return query.executeAndFetchFirst(String.class);
        }
        }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.UserDao#getStudentAchievmentListUsingIds(java.lang.String, java.lang.String)
     */
    @Override
    public List<StudentAchievementDto> getStudentAchievmentListUsingIds(String classId, String studentId) throws Exception
        {
        final String sqlQuery = " SELECT achievement_uuid AS achievementUuid,class_id AS classId,student_id AS studentId,"
                + " achievement_description AS achievementDesc, "
                + " file_name AS fileName,created_date,DATE_FORMAT(modified_date,'%d-%M-%Y %H:%i:%S')as modified_date FROM `cst_achievements`"
                + " WHERE class_id=:classId AND student_id=:studentId ORDER BY modified_date DESC";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery); 
            query.addParameter("classId", classId);
            query.addParameter("studentId", studentId);
            return query.executeAndFetch(StudentAchievementDto.class);
        }
        }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.UserDao#getOldAchievementFileName(java.lang.String)
     */
    @Override
    public String getOldAchievementFileName(String achievementUuid) throws Exception
        {
        final String sqlQuery = " SELECT  file_name AS fileName FROM `cst_achievements` "
                + "   WHERE achievement_uuid=:achievementUuid";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);   
            query.addParameter("achievementUuid", achievementUuid);
            return (String)query.executeScalar();
        }   
        }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.UserDao#deleteStudentAchievementContentData(java.lang.String)
     */
    @Override
    public void deleteStudentAchievementContentData(String achievementUuid) throws Exception
        {
        final String sqlQuery = "delete from cst_achievements where achievement_uuid=:achievementUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("achievementUuid", achievementUuid);
            query.executeUpdate();
        }   
        
            
        }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.UserDao#getStudentAchievmentList()
     */
    @Override
    public List<StudentAchievementDto> getStudentAchievmentList() throws Exception
        {
        final String sqlQuery = "SELECT achievement_uuid AS achievementUuid,"
                + " cst_achievements.class_id AS classId,"
                + " student_id AS studentId,"
                + " achievement_description AS achievementDesc,"
                + " class_name AS className,"
                + " CONCAT(first_name,' ',last_name)AS studentName, "
                + " file_name AS fileName,"
                + " cst_achievements.created_date,"
                + " DATE_FORMAT(cst_achievements.modified_date,'%d-%M-%Y %H:%i:%S')AS modified_date"
                + " FROM `cst_achievements` "
                + " JOIN `cst_class_type` ON `cst_class_type`.class_id=cst_achievements.class_id "
                + " JOIN `cst_users` ON `cst_users`.user_id=`cst_achievements`.student_id "
                + " ORDER BY cst_achievements.modified_date DESC LIMIT 5";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery); 
            return query.executeAndFetch(StudentAchievementDto.class);
        }
        }

    @Override
    public String getNotesMediaUrl(String settingsKey) throws Exception
        {
        final String sqlQuery = "SELECT `media_url` AS url "
                + "FROM `cst_system_settings` AS systemSettings WHERE systemSettings.settings_key=:settingsKey";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("settingsKey", settingsKey);
            return query.executeAndFetchFirst(String.class);
        }
        }
}