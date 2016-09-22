package com.chalk.salt.core.user;

import java.util.List;

import com.chalk.salt.common.dto.DiscussionTopicRequestDto;
import com.chalk.salt.common.dto.GuestUserDto;
import com.chalk.salt.common.dto.ProfilePhotoUploadDto;
import com.chalk.salt.common.dto.TopicImageUploadDto;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.exceptions.UserException;

/**
 * The Interface UserFacade.
 */
public interface UserFacade {

	/**
	 * Gets the user info.
	 *
	 * @param securUuid the secur uuid
	 * @return the user info
	 * @throws UserException the user exception
	 */
	UserDto getUserInfo(final String securUuid) throws UserException;

	/**
	 * Save user info.
	 *
	 * @param userDetails the user details
	 * @return the string
	 * @throws UserException the user exception
	 */
	String saveUserInfo(final UserDto userDetails) throws UserException;
	
	/**
	 * Update profile.
	 *
	 * @param userDetails the user details
	 * @return the boolean
	 * @throws UserException the user exception
	 */
	Boolean updateProfile(final UserDto userDetails)throws UserException;

	/**
	 * Change password.
	 *
	 * @param userDetails the user details
	 * @return the boolean
	 * @throws UserException the user exception
	 */
	Boolean changePassword(final UserDto userDetails)throws UserException;

	/**
	 * Gets the students.
	 *
	 * @return the students
	 * @throws UserException the user exception
	 */
	List<UserDto> getStudents()throws UserException;

	/**
	 * Delete student.
	 *
	 * @param securUuid the secur uuid
	 * @return the boolean
	 * @throws UserException the user exception
	 */
	Boolean deleteStudent(String securUuid)throws UserException;

	/**
	 * Save topic request.
	 *
	 * @param discussionDetails the discussion details
	 * @return the string
	 * @throws UserException the user exception
	 */
	String saveTopicRequest(DiscussionTopicRequestDto discussionDetails)throws UserException;

	/**
	 * Upload profile photo.
	 *
	 * @param securUuid the secur uuid
	 * @param documentUploadData the document upload data
	 * @return the string
	 * @throws UserException the user exception
	 */
	String uploadProfilePhoto(String securUuid,	ProfilePhotoUploadDto documentUploadData)throws UserException;

	/**
	 * Gets the user photo link.
	 *
	 * @param securUuid the secur uuid
	 * @return the user photo link
	 * @throws UserException the user exception
	 */
	String getUserPhotoLink(String securUuid)throws UserException;

	/**
	 * Delete user photo.
	 *
	 * @param securUuid the secur uuid
	 * @throws UserException the user exception
	 */
	void deleteUserPhoto(String securUuid)throws UserException;
	
	/**
	 * Upload topic image.
	 *
	 * @param securUuid the secur uuid
	 * @param documentUploadData the document upload data
	 * @return the string
	 * @throws UserException the user exception
	 */
	String uploadTopicImage(String securUuid,TopicImageUploadDto documentUploadData)throws UserException;

	/**
	 * Gets the topic image link.
	 *
	 * @param securUuid the secur uuid
	 * @return the topic image link
	 * @throws UserException the user exception
	 */
	String getTopicImageLink(String securUuid)throws UserException;

	/**
	 * Upload topic request image.
	 *
	 * @param securUuid the secur uuid
	 * @param documentUploadData the document upload data
	 * @return the string
	 * @throws UserException the user exception
	 */
	String uploadTopicRequestImage(String securUuid, TopicImageUploadDto documentUploadData)throws UserException;

	/**
	 * Save guest user details.
	 *
	 * @param userDetails the user details
	 * @return the string
	 * @throws UserException the user exception
	 */
	GuestUserDto saveGuestUserDetails(GuestUserDto userDetails)throws UserException;

	/**
	 * Reset password.
	 *
	 * @param securUuid the secur uuid
	 * @param tempPassword 
	 * @param encryptedTempPassword2 
	 * @return the boolean
	 * @throws UserException the user exception
	 */
	Boolean resetPassword(String securUuid, String tempPassword, String encryptedTempPassword2)throws UserException;
	
}
