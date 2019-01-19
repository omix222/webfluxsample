package com.example.webflux;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

@SpringBootApplication
@RestController
/**
 * WebFluxサンプルのアプリケーション.
 * @author hiromitakahashi
 *
 */
public class WebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxApplication.class, args);
	}
	/**
	 * 最初のサンプル.
	 * 以下２パターンで動きが変わることを確認する
	 * curl -v localhost:8082
	 * curl -v localhost:8082 -H 'Accept: text/event-stream' 
	 * @return
	 */
	@GetMapping("/time")
	// Accept属性のデフォルトをサーバ側で固定する場合は以下設定を行う
	//(produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> demo(){
		return Flux.interval(Duration.ofMillis(300))
				.map(i -> i + " " + LocalDateTime.now() + "   ")
				.take(10);
	}
	@Bean
	RouterFunction<ServerResponse> routes() {
		return new ReactiveEmployeeController().routes();
}
}
