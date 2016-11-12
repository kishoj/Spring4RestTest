package com.rest.test.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.test.domains.Test;
import com.rest.test.services.TestService;

@RestController
@RequestMapping("/tests")
public class TestRestController {
	@Autowired
	private TestService testService;
	
	@RequestMapping(value = { "/", "" }, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<Test>> getAllTests() {
        List<Test> tests = null;
		try {
			tests = testService.getAllTests();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
        if(tests.isEmpty()){
            return new ResponseEntity<List<Test>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Test>>(tests, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Test> getTaskById(@PathVariable("id") Long id) {
		Test result = null;
		try {
			result = testService.getTestById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == null) {
			return new ResponseEntity<Test>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Test>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = { "/", "" }, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Test> createBatchTask(@RequestBody Test test) {
		Test result = null;
		try {
			result = testService.createTest(test);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Test>(result, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<Test> updateBatchTask(@PathVariable("id") Long id, @RequestBody Test test) {
		Test result = null;
		try {
			result = testService.getTestById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == null) {
			return new ResponseEntity<Test>(HttpStatus.NOT_FOUND);
		}
		if (test.getCreatedBy() != null) {
			result.setCreatedBy(test.getCreatedBy());
		}
		if (test.getCreatedDate() != null) {
			result.setCreatedDate(test.getCreatedDate());
		}
		if (test.getDescription() != null) {
			result.setDescription(test.getDescription());
		}
		try {
			testService.updateTest(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Test>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Test> deleteBatchTask(@PathVariable("id") Long id) {
		Test test = null;
		try {
			test = testService.getTestById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (test == null) {
			return new ResponseEntity<Test>(HttpStatus.NOT_FOUND);
		}
		try {
			testService.deleteTestById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Test>(HttpStatus.NO_CONTENT);
	}
}