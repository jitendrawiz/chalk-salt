/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.dao.user;

import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import org.sql2o.data.Row;
import org.sql2o.data.Table;

import com.chalk.salt.common.dto.AcademicInfoDto;
import com.chalk.salt.common.dto.ChalkSaltConstants;
import com.chalk.salt.common.dto.ParentsInfoDto;
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

        final String sqlQuery = "INSERT INTO cst_users(`user_id`, `first_name`, `middle_name`, `last_name`, `contact_id`, `secur_uuid`,class_id)"
        		+ "VALUES(:userId, :firstName, :middleName, :lastName, :contactId, :securUuid,:studentClass)";

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
    		+ "country, pincode, email, username, mobile, landline, secur_uuid as securUuid ,corsAddress as correspondenceAddress "
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
				+ " WHERE username=:userName and password=:password";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("password", password);
            query.addParameter("newPassword", newPassword);
            query.addParameter("userName", userName);           
            query.executeUpdate();
            return true;
        }        
	}

}