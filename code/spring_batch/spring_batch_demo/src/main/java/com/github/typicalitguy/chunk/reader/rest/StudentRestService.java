package com.github.typicalitguy.chunk.reader.rest;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class StudentRestService {

	private List<StudentRest> students = null;

	public List<StudentRest> getStudents() {
		return new RestTemplate().exchange("http://127.0.0.1:8001/students", HttpMethod.GET,
				new HttpEntity<>(headers()), new ParameterizedTypeReference<List<StudentRest>>() {
				}).getBody();
	}

	private MultiValueMap<String, String> headers() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.put("Content-Type", List.of("application/json"));
		headers.put("Accept", List.of("application/json"));
		return headers;
	}

	public StudentRest getStudent() {
		if (null == students) {
			students = getStudents();
		}
		if (!students.isEmpty()) {
			return students.remove(students.size() - 1);
		}
		return null;
	}
}
