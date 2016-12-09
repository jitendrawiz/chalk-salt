package com.chalk.salt.common.dto;

public class TestGroupDto extends BaseDto
{

    private String testGroupName;

    private String testGroupUuid;

    public String getTestGroupName()
        {
            return testGroupName;
        }

    public void setTestGroupName(String testGroupName)
        {
            this.testGroupName = testGroupName;
        }

    public String getTestGroupUuid()
        {
            return testGroupUuid;
        }

    public void setTestGroupUuid(String testGroupUuid)
        {
            this.testGroupUuid = testGroupUuid;
        }

}
