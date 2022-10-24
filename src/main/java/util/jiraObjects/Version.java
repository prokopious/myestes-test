package util.jiraObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Version {
	public String id;
	public String name;
	public boolean archived;
	public String releaseDate;
	public String description;
	public int projectId;
	public boolean released;
	public String userReleaseDate;
}
