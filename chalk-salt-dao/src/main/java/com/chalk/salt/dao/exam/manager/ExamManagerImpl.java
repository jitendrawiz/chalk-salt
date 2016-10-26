/**
 * 
 */
package com.chalk.salt.dao.exam.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;

import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.AnswerDto;
import com.chalk.salt.common.dto.AnswersDto;
import com.chalk.salt.common.dto.DashBoardDataDto;
import com.chalk.salt.common.dto.DashBoardNotesDto;
import com.chalk.salt.common.dto.DashBoardVediosContentDto;
import com.chalk.salt.common.dto.QuestionDto;
import com.chalk.salt.common.dto.QuestionImageUploadDto;
import com.chalk.salt.common.dto.QuestionListDto;
import com.chalk.salt.common.dto.QuestionOptionsDto;
import com.chalk.salt.common.dto.ResultContentDto;
import com.chalk.salt.common.dto.ResultMasterDto;
import com.chalk.salt.common.dto.ScheduleTestDto;
import com.chalk.salt.common.dto.TestTypeDto;
import com.chalk.salt.common.exceptions.ExamException;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.common.util.SystemSettingsKey;
import com.chalk.salt.dao.exam.ExamDao;
import com.chalk.salt.dao.study.material.StudyMaterialDao;
import com.chalk.salt.dao.user.UserDao;

/**
 * The Class ExamManagerImpl.
 *
 * @author jitendra
 */
public class ExamManagerImpl implements ExamManager
{

    /** The exam dao. */
    @Inject
    private ExamDao examDao;

    /** The user dao. */
    @Inject
    private UserDao userDao;

    /** The study material dao. */
    @Inject
    private StudyMaterialDao studyMaterialDao;

    /** The logger. */
    @Inject
    @AppLogger
    private Logger logger;

    /** The bean mapper. */
    @Inject
    @BeanMapper
    protected Mapper beanMapper;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.chalk.salt.dao.exam.manager.ExamManager#saveQuestion(com.chalk.salt
     * .common.dto.QuestionDto)
     */
    @Override
    public String saveQuestion(QuestionDto questionDetails) throws ExamException
        {
            logger.info("Saving Question ...");
            try
                {
                questionDetails.setQuestionSecuruuid(UUID.randomUUID().toString());

                String questionId = examDao.saveQuestion(questionDetails);
                if (questionId != null)
                    {

                    QuestionOptionsDto optionDto1 = new QuestionOptionsDto();
                    optionDto1.setName(questionDetails.getOptionA());
                    optionDto1.setQuestionId(Integer.parseInt(questionId));

                    QuestionOptionsDto optionDto2 = new QuestionOptionsDto();
                    optionDto2.setName(questionDetails.getOptionB());
                    optionDto2.setQuestionId(Integer.parseInt(questionId));

                    QuestionOptionsDto optionDto3 = new QuestionOptionsDto();
                    optionDto3.setName(questionDetails.getOptionC());
                    optionDto3.setQuestionId(Integer.parseInt(questionId));

                    QuestionOptionsDto optionDto4 = new QuestionOptionsDto();
                    optionDto4.setName(questionDetails.getOptionD());
                    optionDto4.setQuestionId(Integer.parseInt(questionId));

                    switch (questionDetails.getAnswer())
                        {
                        case "A":
                            optionDto1.setIsAnswer(true);
                            optionDto2.setIsAnswer(false);
                            optionDto3.setIsAnswer(false);
                            optionDto4.setIsAnswer(false);
                            optionDto1.setAnswer("A");
                            break;
                        case "B":
                            optionDto1.setIsAnswer(false);
                            optionDto2.setIsAnswer(true);
                            optionDto3.setIsAnswer(false);
                            optionDto4.setIsAnswer(false);
                            optionDto2.setAnswer("B");
                            break;
                        case "C":
                            optionDto1.setIsAnswer(false);
                            optionDto2.setIsAnswer(false);
                            optionDto3.setIsAnswer(true);
                            optionDto4.setIsAnswer(false);
                            optionDto3.setAnswer("C");
                            break;
                        case "D":
                            optionDto1.setIsAnswer(false);
                            optionDto2.setIsAnswer(false);
                            optionDto3.setIsAnswer(false);
                            optionDto4.setIsAnswer(true);
                            optionDto4.setAnswer("D");
                            break;
                        default:
                            System.out.println("Invalid grade");
                        }
                    examDao.saveQuestionOptions(optionDto1);
                    examDao.saveQuestionOptions(optionDto2);
                    examDao.saveQuestionOptions(optionDto3);
                    examDao.saveQuestionOptions(optionDto4);
                    }

                return examDao.getQuestionSecurUuidUsingId(Integer.parseInt(questionId));
                }
            catch (final Exception exception)
                {
                throw new ExamException(ErrorCode.FAIL_TO_SAVE_QUESTION, "Fail to Fetch Question", exception);
                }
        }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.chalk.salt.dao.exam.manager.ExamManager#getQuestions(java.lang.String
     * , java.lang.String)
     */
    @Override
    public List<QuestionDto> getQuestions(String classId, String subjectId) throws ExamException
        {
            logger.info("Fetching list of questions ...");
            try
                {
                List<QuestionDto> questions = examDao.getQuestions(classId, subjectId);
                for (int index = 0; index < questions.size(); index++)
                    {
                    QuestionDto questionDto = questions.get(index);
                    List<QuestionOptionsDto> quesOptionsDto = examDao.getQuestionOptionsUsingQuestionId(questionDto.getQuestionId());
                    for (int j = 0; j < quesOptionsDto.size(); j++)
                        {
                        QuestionOptionsDto optionsDto = quesOptionsDto.get(j);
                        if (j == 0)
                            {
                            questionDto.setOptionA(optionsDto.getName());
                            if (optionsDto.getIsAnswer())
                                {
                                questionDto.setAnswer(optionsDto.getAnswer());
                                }
                            }
                        else
                            if (j == 1)
                                {
                                questionDto.setOptionB(optionsDto.getName());
                                if (optionsDto.getIsAnswer())
                                    {
                                    questionDto.setAnswer(optionsDto.getAnswer());
                                    }
                                }
                            else
                                if (j == 2)
                                    {
                                    questionDto.setOptionC(optionsDto.getName());
                                    if (optionsDto.getIsAnswer())
                                        {
                                        questionDto.setAnswer(optionsDto.getAnswer());
                                        }
                                    }
                                else
                                    if (j == 3)
                                        {
                                        questionDto.setOptionD(optionsDto.getName());
                                        if (optionsDto.getIsAnswer())
                                            {
                                            questionDto.setAnswer(optionsDto.getAnswer());
                                            }
                                        }
                        }
                    }
                return questions;
                }
            catch (final Exception exception)
                {
                throw new ExamException(ErrorCode.FAIL_TO_FETCH_QUESTION_LIST, "Fail to fetch question list", exception);
                }
        }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.chalk.salt.dao.exam.manager.ExamManager#updateQuestionDetails(com
     * .chalk.salt.common.dto.QuestionDto)
     */
    @Override
    public String updateQuestionDetails(QuestionDto question) throws ExamException
        {
            logger.info("Updating Question ...");
            try
                {
                final Date date = new Date();
                final String modifiedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                question.setModifiedDate(modifiedDate);
                String questionUuid = examDao.updateQuestionDetails(question);
                String questionId = examDao.getQuestionIdUsingSecurUuid(questionUuid);
                examDao.deleteQuestionOptions(questionId);
                if (questionId != null)
                    {

                    QuestionOptionsDto optionDto1 = new QuestionOptionsDto();
                    optionDto1.setName(question.getOptionA());
                    optionDto1.setQuestionId(Integer.parseInt(questionId));

                    QuestionOptionsDto optionDto2 = new QuestionOptionsDto();
                    optionDto2.setName(question.getOptionB());
                    optionDto2.setQuestionId(Integer.parseInt(questionId));

                    QuestionOptionsDto optionDto3 = new QuestionOptionsDto();
                    optionDto3.setName(question.getOptionC());
                    optionDto3.setQuestionId(Integer.parseInt(questionId));

                    QuestionOptionsDto optionDto4 = new QuestionOptionsDto();
                    optionDto4.setName(question.getOptionD());
                    optionDto4.setQuestionId(Integer.parseInt(questionId));

                    switch (question.getAnswer())
                        {
                        case "A":
                            optionDto1.setIsAnswer(true);
                            optionDto2.setIsAnswer(false);
                            optionDto3.setIsAnswer(false);
                            optionDto4.setIsAnswer(false);
                            optionDto1.setAnswer("A");
                            break;
                        case "B":
                            optionDto1.setIsAnswer(false);
                            optionDto2.setIsAnswer(true);
                            optionDto3.setIsAnswer(false);
                            optionDto4.setIsAnswer(false);
                            optionDto2.setAnswer("B");
                            break;
                        case "C":
                            optionDto1.setIsAnswer(false);
                            optionDto2.setIsAnswer(false);
                            optionDto3.setIsAnswer(true);
                            optionDto4.setIsAnswer(false);
                            optionDto3.setAnswer("C");
                            break;
                        case "D":
                            optionDto1.setIsAnswer(false);
                            optionDto2.setIsAnswer(false);
                            optionDto3.setIsAnswer(false);
                            optionDto4.setIsAnswer(true);
                            optionDto4.setAnswer("D");
                            break;
                        default:
                            System.out.println("Invalid grade");
                        }
                    examDao.saveQuestionOptions(optionDto1);
                    examDao.saveQuestionOptions(optionDto2);
                    examDao.saveQuestionOptions(optionDto3);
                    examDao.saveQuestionOptions(optionDto4);

                    }
                return questionUuid;
                }
            catch (final Exception exception)
                {
                throw new ExamException(ErrorCode.FAIL_TO_UPDATE_QUESTION, "Fail to update question", exception);
                }
        }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.chalk.salt.dao.exam.manager.ExamManager#deleteQuestion(java.lang.
     * String)
     */
    @Override
    public Boolean deleteQuestion(String questionSecuruuid) throws ExamException
        {
            logger.info("Deleting Question ...");
            try
                {

                String questionId = examDao.getQuestionIdUsingSecurUuid(questionSecuruuid);
                examDao.deleteQuestionOptions(questionId);
                return examDao.deleteQuestion(questionSecuruuid);

                }
            catch (final Exception exception)
                {
                throw new ExamException(ErrorCode.FAIL_TO_DELETE_QUESTION, "Fail to delete question", exception);
                }
        }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.chalk.salt.dao.exam.manager.ExamManager#uploadQuestionImage(java.
     * lang.String, com.chalk.salt.common.dto.QuestionImageUploadDto)
     */
    @Override
    public String uploadQuestionImage(String securUuid, QuestionImageUploadDto documentUploadData) throws ExamException
        {
            logger.info("Uploading Question image");
            try
                {
                String destPath = userDao.getSystemSettings("QUESTION_IMAGE");
                destPath += securUuid.toString();
                File file = new File(destPath);
                if (!file.exists())
                    {
                    file.mkdirs();
                    }
                String fileName = "QUESTION_" + securUuid.toString();
                String oldfileName = examDao.getPreviousQuestionImage(securUuid);
                String destPathToDeleteFile = null;
                String fileNameToSave = fileName + "." + getExtension(documentUploadData.getName());
                if (oldfileName != null)
                    {
                    destPathToDeleteFile = destPath + File.separator + fileName + "." + getExtension(oldfileName);
                    }
                else
                    {
                    destPathToDeleteFile = destPath + File.separator + fileNameToSave;
                    }
                destPath = destPath + File.separator + fileNameToSave;
                File oldfile = new File(destPathToDeleteFile);
                if (oldfile.exists())
                    {
                    oldfile.delete();
                    }
                FileInputStream fin = new FileInputStream(documentUploadData.getFile());
                FileOutputStream fout = new FileOutputStream(destPath);
                int i = 0;
                while ((i = fin.read()) != -1)
                    {
                    fout.write((byte) i);
                    }
                fin.close();
                fout.close();
                examDao.updateQuestionImageDetails(fileNameToSave, securUuid);
                logger.info("Question Image updated successfully in database");
                }
            catch (final Exception exception)
                {
                throw new ExamException(ErrorCode.FAIL_TO_UPDATE_QUESTION_IMAGE, "Fail to update question image", exception);
                }
            return securUuid;
        }

    /**
     * Gets the extension.
     *
     * @param fileName
     *            the file name
     * @return the extension
     */
    private String getExtension(String fileName)
        {
            String extension = "";

            int i = fileName.lastIndexOf('.');
            if (i > 0)
                {
                extension = fileName.substring(i + 1);
                }
            return extension;
        }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.chalk.salt.dao.exam.manager.ExamManager#deleteQuestionImage(java.
     * lang.String)
     */
    @Override
    public void deleteQuestionImage(String questionSecuruuid) throws ExamException
        {

            logger.info("Deleting Question image");
            try
                {
                String destPath = userDao.getSystemSettings("QUESTION_IMAGE");
                destPath += questionSecuruuid.toString();
                String fileName = "QUESTION_" + questionSecuruuid.toString();
                String oldfileName = examDao.getPreviousQuestionImage(questionSecuruuid);
                String destPathToDeleteFile = null;
                if (oldfileName != null)
                    {
                    destPathToDeleteFile = destPath + File.separator + fileName + "." + getExtension(oldfileName);

                    File oldfile = new File(destPathToDeleteFile);
                    if (oldfile.exists())
                        {
                        oldfile.delete();
                        new File(destPath).delete();
                        }
                    }
                logger.info("Question Image deleted successfully");
                }
            catch (final Exception exception)
                {
                throw new ExamException(ErrorCode.FAIL_TO_DELETE_QUESTION_IMAGE, "Fail to delete question image", exception);
                }
        }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.chalk.salt.dao.exam.manager.ExamManager#getDashBoardData(java.lang
     * .String, java.lang.String)
     */
    @Override
    public DashBoardDataDto getDashBoardData(String classId, String subjectId) throws ExamException
        {
            logger.info("Dashboard data fetch start from here ......");
            DashBoardDataDto dashBoardDataDto = null;
            List<DashBoardVediosContentDto> dashBoardVedioList = null;
            List<DashBoardNotesDto> dashBoardNotesList = null;
            try
                {
                dashBoardDataDto = new DashBoardDataDto();
                dashBoardVedioList = examDao.getVediosListByClassAndSubjectId(classId, subjectId);
                dashBoardNotesList = examDao.getNotesListByClassAndSubjectId(classId, subjectId);
                dashBoardDataDto.setVideos(dashBoardVedioList);
                if (dashBoardNotesList.size() > 0)
                    {
                    for (int i = 0; i < dashBoardNotesList.size(); i++)
                        {
                        String notesUuid = dashBoardNotesList.get(i).getNotesUuid();
                        String destPath = userDao.getNotesMediaUrl(SystemSettingsKey.NOTES_FILE.name());
                        String appendedPath = studyMaterialDao.getPathOfNotesUsingNotesUuid(notesUuid);
                        String appPath[] = appendedPath.split("\\$");
                        destPath += String.join(File.separator, appPath[0], appPath[1], notesUuid);
                        String fileName = studyMaterialDao.getOldFileName(notesUuid);
                        String filePathUrl = destPath + File.separator + fileName;
                        dashBoardNotesList.get(i).setFileUrl(filePathUrl);
                        
                        }
                    }
                dashBoardDataDto.setNotes(dashBoardNotesList);
                logger.info("Dashboard data fetch ends here ......");
                return dashBoardDataDto;
                }
            catch (final Exception exception)
                {
                logger.error("Dashboard data fetch exception ......" + exception);
                throw new ExamException(ErrorCode.FAIL_TO_FETCH_DASHBOARD_DATA, "Fail to fetch dashboard data", exception);
                }
        }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.chalk.salt.dao.exam.manager.ExamManager#getQuestionsUsingType(java
     * .lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public List<QuestionListDto> getQuestionsUsingType(String classId, String subjectId, String type,String scheduleTestUuid) throws ExamException
        {
            logger.info("Fetching list of questions ...");
            int limitOfQuestions = 0;
            String typeOfQuestion="Practice Question";
            if (type.equals("cf92fe46-4684-11e6-beb8-9e71128cae77"))
                {
                limitOfQuestions = 40;
                }
            else if (type.equals("cf92fc16-4684-11e6-beb8-9e71128cae77"))
                    {
                    limitOfQuestions = 60;
                    }
                else
                    {
                    limitOfQuestions = 20;
                    }
            
            if(scheduleTestUuid.equals("1")){
            typeOfQuestion="Practice Question";
            }else{
            typeOfQuestion="Scheduled Exam Question";
            }
            try
                {
                List<QuestionListDto> questionsListDto = new ArrayList<QuestionListDto>();
                List<QuestionDto> questionsList = examDao.getQuestionsUsingType(classId, subjectId, limitOfQuestions,typeOfQuestion);
                for (int index = 0; index < questionsList.size(); index++)
                    {
                    QuestionDto questionDto = questionsList.get(index);
                    QuestionListDto questionListObject = new QuestionListDto();
                    questionListObject.setId(questionDto.getQuestionId());
                    questionListObject.setName(questionDto.getQuestion());
                    questionListObject.setOptions(examDao.getQuestionOptionsUsingQuestionId(questionDto.getQuestionId()));
                    questionsListDto.add(questionListObject);
                    }
                return questionsListDto;
                }
            catch (final Exception exception)
                {
                throw new ExamException(ErrorCode.FAIL_TO_FETCH_QUESTION_LIST, "Fail to fetch question list", exception);
                }
        }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.chalk.salt.dao.exam.manager.ExamManager#saveAnswerDetails(com.chalk
     * .salt.common.dto.AnswersDto)
     */
    @Override
    public String saveAnswerDetails(AnswersDto answers) throws ExamException
        {
            logger.info("Saving Answers of test..");
            try
                {
                String testId = examDao.saveAnswerTestRecord(answers);
                List<AnswerDto> answersData = answers.getAnswers();
                for (int i = 0; i < answersData.size(); i++)
                    {
                    AnswerDto answer = answersData.get(i);
                    examDao.saveAnswerDetails(answer, testId);
                    }
                return "success";
                }
            catch (final Exception exception)
                {
                throw new ExamException(ErrorCode.FAIL_TO_SAVE_ANSWERS_LIST, "Fail to save answers list", exception);
                }
        }

    /*
     * (non-Javadoc)
     * 
     * @see com.chalk.salt.dao.exam.manager.ExamManager#getTestTypeList()
     */
    @Override
    public List<TestTypeDto> getTestTypeList() throws ExamException
        {
            logger.info("Fetching list of test type...");
            try
                {
                return examDao.getTestTypeList();
                }
            catch (final Exception exception)
                {
                throw new ExamException(ErrorCode.FAIL_TO_FETCH_TEST_TYPE_LIST, "Fail to fetch test type list", exception);
                }
        }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.chalk.salt.dao.exam.manager.ExamManager#saveScheduleTestData(com.
     * chalk.salt.common.dto.ScheduleTestDto)
     */
    @Override
    public String saveScheduleTestData(ScheduleTestDto scheduleTestDetails) throws ExamException
        {
            logger.info("Saving schedule test data...");

            SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try
                {
                date = dataFormat.parse(scheduleTestDetails.getTestDate());
                }
            catch (ParseException exp)
                {
                throw new ExamException(ErrorCode.FAIL_TO_SAVE_SCHEDULE_TEST, "Fail to save schedule test data");
                }
            if (date.before(new Date())) { throw new ExamException(ErrorCode.FAIL_TO_SAVE_SCHEDULE_TEST, "Data input is not valid,please input valid date after current date"); }
            List<ScheduleTestDto> listOfSchedulerTest = null;
            try
                {
                listOfSchedulerTest = examDao.checkListOfScheduleTest(scheduleTestDetails, date,scheduleTestDetails.getScheduleTestUuid());
                }
            catch (Exception exception)
                {
                throw new ExamException(ErrorCode.FAIL_TO_SAVE_SCHEDULE_TEST, "Fail to save schedule test data", exception);
                }
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
            DateTime inputDateStartTime = formatter.parseDateTime(scheduleTestDetails.getTestDate() + " " + scheduleTestDetails.getTestTime());
            String testDuration = null;
            try
                {
                testDuration = examDao.getTestDurationFromTestTypeUuid(scheduleTestDetails.getTestTypeUuid());
                }
            catch (Exception e)
                {
                e.printStackTrace();
                }
            Integer duration = getTestDuration(testDuration);
            DateTime inputDateEndTime = inputDateStartTime.plusMinutes(duration);

            if (listOfSchedulerTest.size() > 0)
                {

                for (int i = 0; i < listOfSchedulerTest.size(); i++)
                    {
                    ScheduleTestDto databaseData = listOfSchedulerTest.get(i);
                    DateTime databaseinputStartTime = formatter.parseDateTime(databaseData.getTestDate() + " " + databaseData.getTestTime());
                    Integer dbDuration = getTestDuration(databaseData.getTestDuration());
                    DateTime databaseinputEndTime = databaseinputStartTime.plusMinutes(dbDuration);
                    if ((inputDateStartTime.compareTo(databaseinputStartTime) >= 0 && inputDateStartTime.compareTo(databaseinputEndTime) < 0)
                            || (inputDateEndTime.compareTo(databaseinputStartTime) >= 0 && inputDateEndTime.compareTo(databaseinputEndTime) < 0)) { throw new ExamException(
                            ErrorCode.FAIL_TO_SAVE_SCHEDULE_TEST, "Data input is not valid,there is already one scheduled test for this time range."); }

                    }
                }
            try
                {
                scheduleTestDetails.setScheduleTestUuid(UUID.randomUUID().toString());
                return examDao.saveScheduleTestData(scheduleTestDetails, date);
                }
            catch (final Exception exception)
                {
                throw new ExamException(ErrorCode.FAIL_TO_SAVE_SCHEDULE_TEST, "Fail to save schedule test data", exception);
                }
        }

    /**
     * Gets the test duration.
     *
     * @param testDuration the test duration
     * @return the test duration
     */
    private Integer getTestDuration(String testDuration)
        {
            String duration[] = testDuration.split(" ");
            return Integer.parseInt(duration[0]);
        }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.chalk.salt.dao.exam.manager.ExamManager#getScheduleTestsListUsingIds
     * (java.lang.String, java.lang.String)
     */
    @Override
    public List<ScheduleTestDto> getScheduleTestsListUsingIds(String classId, String subjectId) throws ExamException
        {
            logger.info("fetch list of scheduled tests...");
            try
                {
                return examDao.getScheduleTestsListUsingIds(classId, subjectId);
                }
            catch (final Exception exception)
                {
                throw new ExamException(ErrorCode.FAIL_TO_FETCH_SCHEDULE_TEST_LIST, "Fail to Fetch list of Scheduled tests", exception);
                }
        }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.chalk.salt.dao.exam.manager.ExamManager#getScheduleTestContentById
     * (java.lang.String)
     */
    @Override
    public ScheduleTestDto getScheduleTestContentById(String scheduleTestUuid) throws ExamException
        {
            logger.info("fetch scheduled test content using scheduleTestUuid..");
            try
                {
                return examDao.getScheduleTestContentById(scheduleTestUuid);
                }
            catch (final Exception exception)
                {
                throw new ExamException(ErrorCode.FAIL_TO_FETCH_SCHEDULE_TEST_DATA, "Fail to Fetch schedule test content", exception);
                }
        }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.chalk.salt.dao.exam.manager.ExamManager#updateScheduleTestContentDetails
     * (com.chalk.salt.common.dto.ScheduleTestDto)
     */
    @Override
    public void updateScheduleTestContentDetails(ScheduleTestDto scheduleTestContentDetails) throws ExamException
        {
            logger.info("Update schedule test content details....");
            SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try
                {
                date = dataFormat.parse(scheduleTestContentDetails.getTestDate());
                }
            catch (ParseException exp)
                {
                throw new ExamException(ErrorCode.FAIL_TO_UPDATE_SCHEDULE_TEST_DATA, "Fail to update schedule test data");
                }
            if (date.before(new Date())) { throw new ExamException(ErrorCode.FAIL_TO_UPDATE_SCHEDULE_TEST_DATA, "Data input is not valid,please input valid date after current date"); }
            List<ScheduleTestDto> listOfSchedulerTest = null;
            try
                {
                listOfSchedulerTest = examDao.checkListOfScheduleTest(scheduleTestContentDetails, date,scheduleTestContentDetails.getScheduleTestUuid());
                }
            catch (Exception exception)
                {
                throw new ExamException(ErrorCode.FAIL_TO_SAVE_SCHEDULE_TEST, "Fail to save schedule test data", exception);
                }
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
            DateTime inputDateStartTime = formatter.parseDateTime(scheduleTestContentDetails.getTestDate() + " " + scheduleTestContentDetails.getTestTime());
            String testDuration = null;
            try
                {
                testDuration = examDao.getTestDurationFromTestTypeUuid(scheduleTestContentDetails.getTestTypeUuid());
                }
            catch (Exception e)
                {
                // TODO Auto-generated catch block
                e.printStackTrace();
                }
            Integer duration = getTestDuration(testDuration);
            DateTime inputDateEndTime = inputDateStartTime.plusMinutes(duration);

            if (listOfSchedulerTest.size() > 0)
                {

                for (int i = 0; i < listOfSchedulerTest.size(); i++)
                    {
                    ScheduleTestDto databaseData = listOfSchedulerTest.get(i);
                    DateTime databaseinputStartTime = formatter.parseDateTime(databaseData.getTestDate() + " " + databaseData.getTestTime());
                    Integer dbDuration = getTestDuration(databaseData.getTestDuration());
                    DateTime databaseinputEndTime = databaseinputStartTime.plusMinutes(dbDuration);
                    if ((inputDateStartTime.compareTo(databaseinputStartTime) >= 0 && inputDateStartTime.compareTo(databaseinputEndTime) < 0)
                            || (inputDateEndTime.compareTo(databaseinputStartTime) >= 0 && inputDateEndTime.compareTo(databaseinputEndTime) < 0)) { throw new ExamException(
                            ErrorCode.FAIL_TO_UPDATE_SCHEDULE_TEST_DATA, "Data input is not valid,there is already one scheduled test for this time range."); }

                    }
                }

            try
                {
                final Date dateModified = new Date();
                final String modifiedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateModified);
                scheduleTestContentDetails.setScheduleTestModifiedData(modifiedDate);
                examDao.updateScheduleTestContentDetails(scheduleTestContentDetails, date);
                }
            catch (final Exception exception)
                {
                throw new ExamException(ErrorCode.FAIL_TO_UPDATE_SCHEDULE_TEST_DATA, "Fail to update schedule test content", exception);
                }

        }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.chalk.salt.dao.exam.manager.ExamManager#deleteScheduleTestContentData
     * (java.lang.String)
     */
    @Override
    public Boolean deleteScheduleTestContentData(String scheduleTestUuid) throws ExamException
        {
            logger.info("delete schedule test content details...");
            try
                {
                examDao.deleteScheduleTestContentData(scheduleTestUuid);
                return true;
                }
            catch (final Exception exception)
                {
                throw new ExamException(ErrorCode.FAIL_TO_DELETE_SCHEDULE_TEST_CONTENT, "Fail to delete schedule test content", exception);
                }
        }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.exam.manager.ExamManager#getScheduleTestsListUsingClassId(java.lang.String, java.lang.String)
     */
    @Override
    public List<ScheduleTestDto> getScheduleTestsListUsingClassId(String classId, String studentId) throws ExamException
        {
        logger.info("fetch list of scheduled tests...");
        try
            {
            return examDao.getScheduleTestsListUsingClassId(classId,studentId);
            }
        catch (final Exception exception)
            {
            throw new ExamException(ErrorCode.FAIL_TO_FETCH_SCHEDULE_TEST_LIST, "Fail to Fetch list of Scheduled tests", exception);
            }
        }

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.manager.ExamManager#getResultsByClassSubject(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ResultMasterDto> getResultsByClassSubject(String classId, String subjectId, String securUuid)
			throws ExamException {
		logger.info("fetch test results using ClassId, SubjectId and SecurUuid.");
        try
        {
        	return examDao.getResultsByClassSubject(classId, subjectId, securUuid);
        }
        catch (final Exception exception)
        {
        	throw new ExamException(ErrorCode.FAIL_TO_FETCH_RESULT_LIST, "Fail to fetch result list", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.manager.ExamManager#getResultDetailsByTestUuid(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ResultContentDto> getResultDetailsByTestUuid(String classId, String subjectId, String securUuid,
			String testUuid) throws ExamException {
		logger.info("fetch test result details using ClassId, SubjectId, SecurUuid and TestUuid.");
        try
        {
        	return examDao.getResultDetailsByTestUuid(classId, subjectId, securUuid, testUuid);
        }
        catch (final Exception exception)
        {
        	throw new ExamException(ErrorCode.FAIL_TO_FETCH_RESULT_DETAILS, "Fail to fetch result details", exception);
        }
	}
}
