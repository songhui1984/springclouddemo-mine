package cion.springCloudClient.feign;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class HystrixClientFallbackFactory implements FallbackFactory<MyFeignClient> {
	@Override
	public MyFeignClient create(Throwable cause) {
		return new MyFeignClient() {

			@Override
			public String testFeign(String para) {
				cause.printStackTrace();
				return cause.getMessage();
			}

			@Override
			public String testHystrix(String para) {
				cause.printStackTrace();
				return cause.getMessage();
			}
		};
	}
}
