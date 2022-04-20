package dev.maverick.xray.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * Request-Response information stored in the database
 * 
 * @author Shubham Pharande
 *
 */

@Entity
public class RequestResponse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(columnDefinition="CLOB")
	private String requestHeaders;
	
	@Column(columnDefinition="CLOB")
	private String requestBody;
	
	@Column(columnDefinition="CLOB")
	private String url;
	
	private String requestType;
	
	@Column(columnDefinition="CLOB")
	private String responseBody;
	
	private String responseStatus;
	
	@Column(columnDefinition="CLOB")
	private String responseHeaders;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRequestHeaders() {
		return requestHeaders;
	}

	public void setRequestHeaders(String requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(String responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((requestBody == null) ? 0 : requestBody.hashCode());
		result = prime * result + ((requestHeaders == null) ? 0 : requestHeaders.hashCode());
		result = prime * result + ((requestType == null) ? 0 : requestType.hashCode());
		result = prime * result + ((responseBody == null) ? 0 : responseBody.hashCode());
		result = prime * result + ((responseHeaders == null) ? 0 : responseHeaders.hashCode());
		result = prime * result + ((responseStatus == null) ? 0 : responseStatus.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestResponse other = (RequestResponse) obj;
		if (id != other.id)
			return false;
		if (requestBody == null) {
			if (other.requestBody != null)
				return false;
		} else if (!requestBody.equals(other.requestBody))
			return false;
		if (requestHeaders == null) {
			if (other.requestHeaders != null)
				return false;
		} else if (!requestHeaders.equals(other.requestHeaders))
			return false;
		if (requestType == null) {
			if (other.requestType != null)
				return false;
		} else if (!requestType.equals(other.requestType))
			return false;
		if (responseBody == null) {
			if (other.responseBody != null)
				return false;
		} else if (!responseBody.equals(other.responseBody))
			return false;
		if (responseHeaders == null) {
			if (other.responseHeaders != null)
				return false;
		} else if (!responseHeaders.equals(other.responseHeaders))
			return false;
		if (responseStatus == null) {
			if (other.responseStatus != null)
				return false;
		} else if (!responseStatus.equals(other.responseStatus))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RequestResponse [id=" + id + ", requestHeaders=" + requestHeaders + ", requestBody=" + requestBody
				+ ", url=" + url + ", requestType=" + requestType + ", responseBody=" + responseBody
				+ ", responseStatus=" + responseStatus + ", responseHeaders=" + responseHeaders + "]";
	}

}
