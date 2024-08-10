package com.jobapplication.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class JobApplication {
	static List<String> arr = new ArrayList<>();

	@PostMapping("/add")
	public List<String> addList(@RequestParam String item)
	{
		arr.add(item);
		return arr;
	}
	@DeleteMapping("/delete")
	public List<String> deleteList(@RequestParam int item) {
		arr.remove(item);
		return arr;
	}
	@DeleteMapping("/deleteAll")
	public List<String> deleteAll(@RequestParam int item) {
		arr = new ArrayList<>();
		return arr;
	}
	@PatchMapping("/update")
	public ResponseEntity<List<String>> updateList(@RequestBody Map<String, String> requestBody) {
		String oldItem = requestBody.get("oldItem");
		String newItem = requestBody.get("newItem");

		int index = arr.indexOf(oldItem);

		if (index != -1) {
			arr.set(index, newItem);
			return ResponseEntity.ok(arr);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(arr);
		}
	}

	@GetMapping("/getItem/{item}")
	public int getItem(@PathVariable String item)
	{
		return arr.indexOf(item);
	}

	@GetMapping("/hello")
	public List<String> getList()
	{
		return arr;
	}

	public static void main(String[] args) {
		SpringApplication.run(JobApplication.class, args);
		System.out.println("yehh");


	}

}
