package com.hex.cluster.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<ResponseObject> handleApiException(RestApiException e) {
		final String title = e.getTitle();
		final String message = e.getMessageCode();
		return ResponseEntity.status(e.getStatus()).body(new ResponseObject(title, message));
	}

	public class ResponseObject {
		private String title;
		private String message;

		public ResponseObject(final String title, final String message) {
			this.setTitle(title);
			this.setMessage(message);
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}
