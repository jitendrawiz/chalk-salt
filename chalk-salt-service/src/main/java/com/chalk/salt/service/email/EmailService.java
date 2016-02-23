package com.chalk.salt.service.email;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PreDestroy;
import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;

import com.chalk.salt.common.dto.EmailNotificationDto;
import com.chalk.salt.common.dto.NotificationMessageDto;
import com.chalk.salt.common.exceptions.SystemException;
import com.chalk.salt.common.exceptions.UserException;


/**
 * The Class EmailService.
 */
@Singleton
public class EmailService {

    /** The Constant LOGGER. */
    protected final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    /** The Constant EMAIL_DELIMITER_REGEX. */
    private static final String EMAIL_DELIMITER_REGEX = "[ ,;:]+";

    /** The Constant executorService. */
    private static final ExecutorService executorService = Executors.newFixedThreadPool(1);

    /**
     * Send mail.
     *
     * @param notificationMessage the notification message
     * @return true, if successful
     * @throws UserException the core exception
     */
    public boolean sendMail(final NotificationMessageDto notificationMessage) throws UserException{
    	try{
           return sendEmail(notificationMessage);
        } catch (final EmailException exception) {
            throw new UserException("An error occured while sending email to registered user", exception);
        }
        
        
    }
    

   /**
    * Send email.
    *
    * @param notificationMessage the notification message
    * @return true, if successful
    * @throws EmailException the email exception
    */
   private boolean sendEmail(final NotificationMessageDto notificationMessage) throws EmailException{
	 final ImageHtmlEmail imageHtmlEmail = new ImageHtmlEmail();
     final EmailNotificationDto emailNotification = (EmailNotificationDto) notificationMessage;
         final String to = emailNotification.getTo();
         final String[] recipientList = parseRecipientList(to);
         imageHtmlEmail.addTo(recipientList);
         imageHtmlEmail.setSubject(emailNotification.getSubject());
         imageHtmlEmail.setDataSourceResolver(new DataSourceUrlResolver(null));
         imageHtmlEmail.setFrom("chalkandsalt@gmail.com");
         imageHtmlEmail.setHostName("smtp.gmail.com");
         imageHtmlEmail.setSmtpPort(465);
         imageHtmlEmail.setAuthenticator(new DefaultAuthenticator("chalkandsalt", "ch@lkands@lt"));
         imageHtmlEmail.setSSLOnConnect(true);;            
         imageHtmlEmail.setHtmlMsg(notificationMessage.getBody());
         final List<String> attachments = emailNotification.getAttachments();
         if (attachments != null && attachments.size() > 0) {
             for (final String attachment : attachments) {
                 imageHtmlEmail.attach(new File(attachment));
             }
         }
         executorService.execute(new Runnable() {

             @Override
             public void run() {
                 try {
                     imageHtmlEmail.send();
                 } catch (final EmailException exception) {
                     LOGGER.error("An error occurred while sending the email", exception);
                 }
             }
         });
      
         return true;
   }
    /**
     * Destroy executor.
     */
    @PreDestroy
    private void destroyExecutor(){
        executorService.shutdown();
    }

    /**
     * Sets the session.
     *
     * @param to the to
     * @return the string[]
     */
    /*
     * public void setSession(final Session session) { emailSession = session; }
     */

    /**
     * Parses the recipient list.
     *
     * @param to the to
     * @return the string[]
     */
    private String[] parseRecipientList(String to) {
        final Set<String> recipientList = new HashSet<String>();
        to = StringUtils.defaultString(to);
        final String[] emailList = StringUtils.split(to, EMAIL_DELIMITER_REGEX);
        for (final String email : emailList) {
            final boolean emailValid = EmailValidator.getInstance(true).isValid(email);
            if (emailValid) {
                recipientList.add(email);
            } else {
                LOGGER.error("Invalid email address found while send an email. Ignoring following: {}", email);
            }
        }
        return recipientList.toArray(new String[0]);
    }


	/**
	 * Send e mail enquiry.
	 *
	 * @param emailNotification the email notification
	 * @return the boolean
	 * @throws SystemException the system exception
	 */
	public Boolean sendEMailEnquiry(EmailNotificationDto emailNotification) throws SystemException {
		try{
	           return sendEmail(emailNotification);
	        } catch (final EmailException exception) {
	            throw new SystemException("An error occured while sending email for enquiry", exception);
	        }
	        
	}
}
