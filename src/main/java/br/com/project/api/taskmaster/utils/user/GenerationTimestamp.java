package br.com.project.api.taskmaster.utils.user;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class GenerationTimestamp {

	public LocalDateTime timestampGenerator() {
		return LocalDateTime.now();
	}
}
