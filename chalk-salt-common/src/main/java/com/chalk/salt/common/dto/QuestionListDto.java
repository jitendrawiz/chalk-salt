package com.chalk.salt.common.dto;

import java.util.List;

/**
 * The Class QuestionListDto.
 */
public class QuestionListDto {
    
    /** The Id. */
    private Integer Id;
    
    /** The Name. */
    private String Name;    
    
    /** The Options. */
    private List<QuestionOptionsDto> Options;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
        return Id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Integer id) {
        Id = id;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return Name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Gets the options.
     *
     * @return the options
     */
    public List<QuestionOptionsDto> getOptions() {
        return Options;
    }

    /**
     * Sets the options.
     *
     * @param options the new options
     */
    public void setOptions(List<QuestionOptionsDto> options) {
        Options = options;
    }
    
    

}
