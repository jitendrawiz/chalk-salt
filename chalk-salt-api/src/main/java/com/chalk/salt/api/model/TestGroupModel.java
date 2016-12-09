package com.chalk.salt.api.model;

public class TestGroupModel extends ApiModel
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
