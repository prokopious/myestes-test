package util.jira;
import static io.restassured.RestAssured.given;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import testBase.TestBase;
import util.jiraObjects.CreateIssueDetails;
import util.jiraObjects.Issue;
import util.jiraObjects.IssueUpdate;
import util.jiraObjects.Project;
import util.jiraObjects.ProjectIssuesReturn;
import util.jiraObjects.Version;


public class Client extends TestBase {
	public static String baseUri="https://estesexpress.atlassian.net";
	public String contentType = "";
	private String encodedCreds;
	private Gson g = new Gson();
	
	private Logger logger = LogManager.getLogger(Client.class);
	
	public HashMap<String, String> headers = new HashMap<String, String>();

	//Connection Setup
	//Gets
	public Issue getIssue(String issueKey) {
		headers.clear();
		headers.put("Authorization", "Basic "+encodedCreds);
		headers.put("Accept","application/json");

		Response res = get("/rest/api/3/issue/" + issueKey);
		Issue issueJson = res.then().log().ifError().extract().as(Issue.class);
		return new Issue();
	}

	public Version createVersion(Version version){
		headers.clear();
		headers.put("Authorization", "Basic "+encodedCreds);
		headers.put("Accept","application/json");
		contentType="application/json";
		JsonObject object = new JsonObject();
		object.addProperty("archived",version.archived);
		object.addProperty("releaseDate",version.releaseDate);
		object.addProperty("name",version.name);
		object.addProperty("description",version.description);
		object.addProperty("projectId",version.projectId);
		object.addProperty("released",version.released);
		Response res = post("/rest/api/3/version",g.toJson(object));

		String bodyString = res.getBody().asString();
		if(!Boolean.parseBoolean(getJavaPropertiesManager().readProperty("isJenkins"))) {
			return g.fromJson(res.then().log().ifError().extract().asString(), Version.class);
		}
		else{
			return g.fromJson(res.then().extract().asString(), Version.class);
		}
	}

	public Version[] getVersions(String projectId){
		headers.clear();
		headers.put("Authorization", "Basic "+encodedCreds);
		headers.put("Accept","application/json");
		contentType="application/json";
		Response res=get("/rest/api/3/project/"+projectId+"/versions");
		//String bodyString = res.getBody().asString();
		if(!Boolean.parseBoolean(getJavaPropertiesManager().readProperty("isJenkins"))) {
			return g.fromJson(res.then().log().ifError().extract().asString(), Version[].class);
		}
		else{
			return g.fromJson(res.then().extract().asString(), Version[].class);
		}
	}



	
	/**
	 * @modifiers Includes text, summary, description... more to be included later. 
	 * These can be used to filter out some issues. If left null, all issues for 
	 * the provided project key will be returned. Otherwise will return only the issues
	 * that meet the modifier requirements. Ex. modifier "summary" with value "Test" will 
	 * only return issues with the String "Test" in the summary field.
	 * 
	 * */
	public List<Issue> getAllIssues(String projectKey, String modifier, String modifierValue) {
		headers.clear();
		headers.put("Authorization", "Basic "+encodedCreds);
		headers.put("Accept","application/json");
		
		List<Issue> issues = new ArrayList<Issue>();
		int startAt =0;
		int total =0;
		
		do {
			String resource = "/rest/api/latest/search?startAt="+startAt+"&jql=project="+projectKey;
			if(modifier!=null&&modifierValue!=null) {
				resource += " and " + modifier + " ~ " + modifierValue;
			}
			
			Response res = get(resource);
			ProjectIssuesReturn partialReturn = res.then().log().all().extract().as(ProjectIssuesReturn.class);
			for(Issue i:partialReturn.issues) {
				issues.add(i);
			}
			startAt += partialReturn.maxResults;
			total = partialReturn.total;
		}while(startAt <total);
		
		return issues;
	}
	
	public List<Issue> getIssuesByJQL(String projectKey, String JQL) {
		headers.clear();
		headers.put("Authorization", "Basic "+encodedCreds);
		headers.put("Accept","application/json");
		
		List<Issue> issues = new ArrayList<Issue>();
		int startAt =0;
		int total =0;
		
		do {
			String resource = "/rest/api/latest/search?startAt="+startAt+"&jql=project="+projectKey;
			if(JQL!=null&&JQL!="") {
				resource += " and " + JQL;
			}
			
			Response res = get(resource);
			ProjectIssuesReturn partialReturn = res.then().log().all().extract().as(ProjectIssuesReturn.class);
			for(Issue i:partialReturn.issues) {
				issues.add(i);
			}
			startAt += partialReturn.maxResults;
			total = partialReturn.total;
		}while(startAt <total);
		
		return issues;
	}
	
	/**
	 * Returns project information and associated versions
	 * */
	public Project getProject(String project) {
		headers.clear();
		headers.put("Authorization", "Basic "+encodedCreds);
		headers.put("Accept","application/json");
		
		return get("/rest/api/latest/project/"+project).then().log().all().extract().as(Project.class);
	}
	
	//Put
	public int updateIssue(IssueUpdate update) {
		headers.clear();
		headers.put("Authorization", "Basic "+encodedCreds);
		
		String json = update.getJson();
		Response res = put("/rest/api/latest/issue/"+update.issue,json);
		//String bodyString = res.getBody().asString();
		int success = res.getStatusCode();
		return success;
	}

	//Post
	public Issue createIssue(CreateIssueDetails ci) {
		headers.clear();
		headers.put("Authorization", "Basic "+encodedCreds);
		
		Response res = post("/rest/api/latest/issue",ci.getJson());

		Issue issue = res.then().log().all().extract().as(Issue.class);
		//String bodyString = res.getBody().asString();
		return issue;
	}
	
	public int addAttachment(String issueKey, File file) {
		headers.clear();
		headers.put("Authorization", "Basic "+encodedCreds);
		headers.put("X-Atlassian-Token", "no-check");
		
		Response res = post("/rest/api/latest/issue/"+issueKey+"/attachments",file);
		//String bodyString = res.getBody().asString();
		return res.getStatusCode();
	}
	//Connections
	
	private Response post(String resource, File body) {
		RequestSpecification request = given().baseUri(baseUri);
		if(contentType != null&&!contentType.contentEquals("")) {
			request = request.contentType(contentType);
		}
		if(headers != null && headers.size()>0) {
			request = request.headers(headers);
		}
		request.log().all();
		return request.when().multiPart(body).post(resource).andReturn();
	}
	
	private Response post(String resource, Object body) {
		RequestSpecification request = given().baseUri(baseUri);
		if(contentType != null&&!contentType.contentEquals("")) {
			request = request.contentType(contentType);
		}
		if(headers != null && headers.size()>0) {
			request = request.headers(headers);
		}
		request.log().all();
		return request.when().body(body).post(resource).andReturn();
	}

	private Response put(String resource,Object body) {
		RequestSpecification request = given().baseUri(baseUri);
		if(contentType != null && !contentType.equals("")) {
			request = request.contentType(contentType);
		}
		if(headers != null && headers.size()>0) {
			request = request.headers(headers);
		}
		request.log().all();
		return request.when().body(body).put(resource);
	}

	private Response get(String resource) {
		RequestSpecification request = given().baseUri(baseUri);
		if (!contentType.equals("")) {
			request = request.contentType(contentType);
		}
		if (headers.size() > 0) {
			request = request.headers(headers);
		}
		return request.log().all().when().get(resource).andReturn();
	}

	/*
	private Response delete() {
		return null;
	}
	*/
	
	public static String encodeCredentials(String username, String apiKey) {
		String creds = username + ":" + apiKey;
		String encoded = "";
		try {
			byte[] credentials = creds.getBytes("UTF8");
			encoded = Base64.getEncoder().encodeToString(credentials);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return encoded;
	}
	
	public Client(String username, String password) {
		this.encodedCreds = encodeCredentials(username,password);

	}
}
