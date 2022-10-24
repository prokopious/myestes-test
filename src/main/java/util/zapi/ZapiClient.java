package util.zapi;

import static io.restassured.RestAssured.given;

import com.github.javafaker.Bool;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import testBase.TestBase;
import util.zapiObjects.CycleDetails;
import util.zapiObjects.CycleList;
import util.zapiObjects.Execution;
import util.zapiObjects.ExecutionDetails;
import util.zapiObjects.ProjectCycles;


public class ZapiClient extends TestBase {
	private Logger logger = LogManager.getLogger(ZapiClient.class);
	
	private final String baseUri = "https://prod-api.zephyr4jiracloud.com";
	private final String contextPath = "/connect";
	private final Boolean isJenkins=Boolean.parseBoolean(getJavaPropertiesManager().readProperty("isJenkins"));
	private String zAccessKey;
	private String zSecretKey;
	private long experation = 2000;
	private String accId = "";
	public String contentType="";
	public void setAccountId(String id) {
		accId = id;
	}
	public void setExperation(long time) {
		experation = time;
	}
	public Map<String,String> headers = new HashMap<String,String>();
	Map<String,Object> claims = new HashMap<String,Object>();
	Gson g = new Gson();
	public void setAccessKey(String key) {
		zAccessKey = key;
	}
	public void setSecretKey(String key) {
		zSecretKey = key;
	}
	public void addTestToCycle(String cycleId,String projectId,String versionId,String[] tests) throws Exception {
		String relativePath = "/public/rest/api/1.0/executions/add/cycle/"+cycleId;
		///public/rest/api/1.0/executions/add/cycle/44534520-177e-431a-9e2a-c1d50034293b
		String cannonicalPath = "POST&"+relativePath+"&";

		setClaims(cannonicalPath);
		String jwt = getJwt(claims,zSecretKey);

		setHeaders(jwt);

		String json = "{\"versionId\":\""+versionId+"\",\"projectId\":\""+projectId+"\",\"method\":\"1\",\"issues\":[";
		for(int i = 0; i < tests.length; i ++) {
			if(i>0) {
				json+=",";
			}
			json+="\""+tests[i]+"\"";
		}
		json+="]}";
		
		Response r = post(baseUri+contextPath+relativePath,json);
		
		if(!(r.getStatusCode()>=200&&r.getStatusCode()<300)) {
			throw new Exception("Failed to add tests to cycle "+cycleId);
		}
		
		
	}

	private void setClaims(String cannonicalPath) {
		claims.clear();
		claims.put("sub", accId);
		claims.put("qsh", getQsh(cannonicalPath));
		claims.put("iss", zAccessKey);
		claims.put("iat", System.currentTimeMillis());
		claims.put("exp", System.currentTimeMillis() + experation);
	}

	public boolean createExecution(ExecutionDetails details){
		////set headers and create jwt token
		String relativePath = "https://prod-api.zephyr4jiracloud.com/connect/public/rest/api/1.0/execution";
		String cannonicalPath = "POST&"+relativePath+"&";
		setClaims(cannonicalPath);
		String jwt =getJwt(claims,zSecretKey);
		setHeaders(jwt);

		JsonObject json = new JsonObject();
		JsonObject array =new JsonObject();
		array.addProperty("id",details.status.id);

		json.add("status",array);
		json.addProperty("projectId",details.projectId);
		json.addProperty("issueId",details.issueId);
		json.addProperty("cycleId",details.cycleId);
		json.addProperty("versionId",details.versionId);
		logger.info(g.toJson(json));

		Response r = post(baseUri+contextPath+relativePath,g.toJson(json));
		r.then().log().all();
		return r.statusCode()==200;


	}
	public boolean createAttachment(ExecutionDetails details, File file, String mimeType) {

		String relativePath = "/public/rest/api/1.0/attachment";
		Object[] queryValues={details.issueId,details.versionId,details.status.name,details.cycleId,details.id,details.projectId,file.getName()};
		FormattingTuple querryString = MessageFormatter.arrayFormat("issueId={}&versionId={}&entityName={}&cycleId={}&entityId={}&projectId={}&comment={}"
				,queryValues);
				String cannonicalPath = "POST&"+relativePath+"&"+querryString.getMessage();

		setClaims(cannonicalPath);
		String jwt = getJwt(claims,zSecretKey);

		headers.clear();
		headers.put("Authorization", "JWT " + jwt);
		headers.put("zapiAccessKey", zAccessKey);
		//headers.put("Content-Type", "multipart/json");




		Response r = postAttachment(baseUri+contextPath+relativePath,file,mimeType);

		return r.statusCode()==200;
	}
	public ProjectCycles createCycle(String name, String versionId, String projectId) {

		String relativePath = "/public/rest/api/1.0/cycle";
		String cannonicalPath = "POST&"+relativePath+"&";

		setClaims(cannonicalPath);
		String jwt = getJwt(claims,zSecretKey);

		setHeaders(jwt);

		String json = "{\"name\":\""+name+"\",\"versionId\":\""+versionId+"\",\"projectId\":\""+projectId+"\"}";

		Response r = post(baseUri+contextPath+relativePath,json);

		return g.fromJson(r.then().log().all().extract().asString(),ProjectCycles.class);
	}

	private void setHeaders(String jwt) {
		headers.clear();
		headers.put("Authorization", "JWT " + jwt);
		headers.put("zapiAccessKey", zAccessKey);
		headers.put("Content-Type", "application/json");
	}

	public CycleDetails getCycle(String projectId, String versionId, String cycleId) {
		CycleDetails cycle = new CycleDetails();
		if(experation ==0) {
			experation =2000;
		}
		String relativePath = "/public/rest/api/1.0/executions/search/cycle/"+cycleId;
		String querryStringBase = "offset={}&projectId={}&versionId={}";
		
		int total =0;
		Object[] params= new Object[3];
		params[0] = 0;
		params[1] = projectId;
		params[2] = versionId;
		
		do {
		String querryString = MessageFormatter.arrayFormat(querryStringBase, params).getMessage();		
		String cannonicalPath = "GET&"+relativePath+"&"+querryString;

			setClaims(cannonicalPath);

			String jwt = getJwt(claims,zSecretKey);
		
		headers.clear();
		headers.put("Authorization", "JWT "+jwt);
		headers.put("zapiAccessKey",zAccessKey);
		contentType="text/plain";
		
		Response r = get(contextPath+relativePath+"?"+querryString);
		//cycle = g.fromJson(r.then().log().all().extract().asString(),ProjectCycles[].class);
		if(cycle.searchObjectList==null) {
			try {
				cycle = g.fromJson(r.then().extract().asString(),CycleDetails.class);
			} catch (JsonSyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("The error message is: " + e);
			}
			System.out.println("Total records expected: "+cycle.totalCount);
		}
		else {
			CycleDetails partial;
			partial =g.fromJson(r.then().log().all().extract().asString(),CycleDetails.class);
			CycleDetails temp = new CycleDetails();
			temp.searchObjectList = new Execution[cycle.searchObjectList.length+partial.searchObjectList.length];
			System.arraycopy(cycle.searchObjectList, 0, temp.searchObjectList, 0, cycle.searchObjectList.length);
			System.arraycopy(partial.searchObjectList, 0, temp.searchObjectList, cycle.searchObjectList.length, partial.searchObjectList.length);
			cycle.searchObjectList = temp.searchObjectList;
		}
		params[0] = (int)params[0]+cycle.maxAllowed;
		if(total<1) {
			total = cycle.totalCount;			
		}
		}while((int)params[0]<total);
		
		return cycle;
	}
	
	public CycleList getCycleList(String projectId, String versionId) {
		CycleList list = new CycleList();
		if(experation ==0) {
			experation =2000;
		}
		String relativePath = "/public/rest/api/1.0/cycles/search";
		String querryString = MessageFormatter.format("projectId={}&versionId={}",projectId,versionId).getMessage();
		String cannonicalPath = "GET&"+relativePath+"&"+querryString;

		setClaims(cannonicalPath);

		String jwt = getJwt(claims,zSecretKey);
		headers.clear();
		headers.put("Authorization", "JWT "+jwt);
		headers.put("zapiAccessKey",zAccessKey);
		contentType="text/plain";

		
		Response r = get(contextPath+relativePath+"?"+querryString);
		if(isJenkins) {
			list.cycles = g.fromJson(r.then().log().all().extract().asString(), ProjectCycles[].class);
		}
		else{
			list.cycles=g.fromJson(r.then().extract().asString(),ProjectCycles[].class);
		}
		return list;
	}



	public boolean deleteCycle(ProjectCycles cycle){

		String relativePath = "/public/rest/api/1.0/cycle";
		String cannonicalPath = "DELETE&"+relativePath+"&"+"";
		setClaims(cannonicalPath);
		String jwt=getJwt(claims,zSecretKey);
		setHeaders(jwt);

		String json=CycleList.createCycleJson(cycle.versionId,cycle.projectId,cycle.id);
		Response r = null;
		try {
			r = delete(baseUri+contextPath+relativePath);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return (r != null ? r.statusCode() : BigDecimal.ZERO.intValue()) ==200;
	}
	
	public boolean updateExecution(ExecutionDetails details) {
		////set headers and create jwt token
		String relativePath = "/public/rest/api/1.0/executions";
		String cannonicalPath = "POST&"+relativePath+"&";
		setClaims(cannonicalPath);
		String jwt =getJwt(claims,zSecretKey);
		setHeaders(jwt);
		//get executions in json format
		JsonArray array = new JsonArray();
		array.add(details.id);
		////
		logger.info(details.id+details.status.id);
		logger.info(array.getAsString());
		JsonObject json = new JsonObject();
		json.add("executions",array);
		json.addProperty("status",details.status.id);
		json.addProperty("clearDefectMappingFlag",false);
		json.addProperty("testStepStatusChangeFlag",true);
		json.addProperty("stepStatus",-1);
		logger.info(g.toJson(json));

		Response r = post(baseUri+contextPath+relativePath,g.toJson(json));

		return r.statusCode() == 200;
	}
	
	private Response get(String resource) {
		RequestSpecification request = given().baseUri(baseUri);
		if (!contentType.equals("")) {
			request = request.contentType(contentType);
		}
		if (headers.size() > 0) {
			request = request.headers(headers);
		}
		if(!isJenkins) {
			return request.when().get(resource).andReturn();
		}
		else{
			return request.when().get(resource).andReturn();
		}
	}
	
	private Response put(String resource,Object body) {
		RequestSpecification request = given().baseUri(baseUri);
		if(contentType != null && !contentType.equals("")) {
			request = request.contentType(contentType);
		}
		if(headers != null && headers.size()>0) {
			request = request.headers(headers);
		}
		if(!isJenkins) {
			request.log().all();
		}
		return request.when().body(body).put(resource);
	}
	
	private Response post(String resource, File body) {
		RequestSpecification request = given().baseUri(baseUri);
		if(contentType != null&&!contentType.contentEquals("")) {
			request = request.contentType(contentType);
		}
		if(headers != null && headers.size()>0) {
			request = request.headers(headers);
		}
		if(!isJenkins) {
			request.log().all();
		}
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
		if(!isJenkins) {
			request.log().all();
		}
		return request.when().body(body).post(resource).andReturn();
	}
	private Response postAttachment(String resource, File file, String mimeType) {
		RequestSpecification request = given().baseUri(baseUri);


		if(headers != null && headers.size()>0) {
			request = request.headers(headers);
		}
		if(!isJenkins) {
			request.log().all();
		}
		return request.given()
//				.multiPart(new MultiPartSpecBuilder(file)
//						.fileName(file.getName())
//						.controlName("file")
//						.mimeType(mimeType)
//						.build())
				.multiPart(file)
				.when()
				.post(resource);
	}
	private Response delete(String url) throws MalformedURLException {
		RequestSpecification request = given().baseUri(baseUri);
		if(contentType != null&&!contentType.contentEquals("")) {
			request = request.contentType(contentType);
		}
		if(headers != null && headers.size()>0) {
			request = request.headers(headers);
		}
		if(!isJenkins) {
			request.log().all();
		}
		return request.delete(new URL(url));
	}

	public String getQsh(String qstring) {
		String qsh = "";
		try {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] bytes = digest.digest(qstring.getBytes(StandardCharsets.UTF_8));

		qsh = bytesToHex(bytes);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return qsh;
	}

	private String bytesToHex(byte[] bytes) {
		StringBuffer buffer = new StringBuffer();
		for (byte b : bytes) {
			String hex = String.format("%02x", b);
			if (hex.length() == 1) {
				buffer.append('0');
			}
			buffer.append(hex);
		}
		return buffer.toString();
	}

	public String getJwt(Map<String,Object> claims, String secret) {
		try {
			
		SignatureAlgorithm signature = SignatureAlgorithm.HS256;
		
		//byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		byte[] apiKeySecretBytes = secret.getBytes("UTF-8");
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signature.getJcaName());
		JwtBuilder builder = Jwts.builder();
		builder.setHeaderParam("typ", "JWT");
		builder.setClaims(claims);
		//builder.signWith(signingKey, signature);
		builder.signWith(signingKey);
		//builder.signWith(signature,signingKey);
		logger.info(builder.compact());
		return builder.compact();

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}

	public void deleteRunner(String projectId,String versionId){
		Arrays.stream(getCycleList(projectId,versionId).cycles).forEach(cycle->{
			if(LocalDate.now().minusDays(2).isAfter(LocalDate.parse(cycle.creationDate))){
				deleteCycle(cycle);
			}
		});
	}
}