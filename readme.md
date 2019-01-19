# SpringWebFluxのサンプル

## アクセス方法

	 * curl -v localhost:8082/time
	 * curl -v localhost:8082/time -H 'Accept: text/event-stream' 
	 * curl -v localhost:8082/employee -H 'Accept: application/stream+json'
	 * curl -v localhost:8082/employee -H 'Accept: application/stream+json' -X POST -d "{\"id\":\"id001\",\"name\":\"taka\"}" -H "Content-Type:application/json"

- 参考
- http://spring-boot-reference.jp/webflux%E3%81%A7%E3%82%B9%E3%83%88%E3%83%AA%E3%83%BC%E3%83%A0/
- https://github.com/spring-boot-sample/spring-webflux-sample/blob/master/src/main/java/com/sample/webflux/controller/ReactiveTemperatureController.java

- https://blog.ik.am/entries/417