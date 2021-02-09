package starter.testing.core.util.report.browserstack.entity;

public class BrowserStackProject {

    private long projectId;
    private String projectName;
    private long userId;

    public BrowserStackProject(long projectId, String projectName, long userId) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.userId = userId;
    }

    public long getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public long getUserId() {
        return userId;
    }
}
