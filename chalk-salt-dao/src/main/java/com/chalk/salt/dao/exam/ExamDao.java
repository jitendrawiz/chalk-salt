package com.chalk.salt.dao.exam;

import com.chalk.salt.common.dto.QuestionDto;

public interface ExamDao {

	public String saveQuestion(QuestionDto questionDetails)throws Exception;

}
