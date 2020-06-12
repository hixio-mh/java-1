package io.ipdata.client;

import feign.httpclient.ApacheHttpClient;
import io.ipdata.client.error.IpdataException;
import io.ipdata.client.model.IpdataModel;
import io.ipdata.client.service.IpdataService;
import lombok.SneakyThrows;
import net.javacrumbs.jsonunit.JsonAssert;
import net.javacrumbs.jsonunit.core.Option;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class FullModelTest {

  private static final TestContext TEST_CONTEXT = new TestContext("https://api.ipdata.co");

  @Parameterized.Parameter
  public IpdataService ipdataService;

  @Test
  @SneakyThrows
  public void testFullResponse() {
    JsonAssert.setOptions(Option.TREATING_NULL_AS_ABSENT, Option.IGNORING_EXTRA_FIELDS);
    IpdataModel ipdataModel = ipdataService.ipdata("8.8.8.8");
    String actual = TEST_CONTEXT.mapper().writeValueAsString(ipdataModel);
    String expected = TEST_CONTEXT.get("/8.8.8.8", null);
    TEST_CONTEXT.assertEqualJson(actual, expected, TEST_CONTEXT.configuration().whenIgnoringPaths("time_zone.current_time"));
    if (ipdataService == TEST_CONTEXT.cachingIpdataService()) {
      //value will be returned from cache now
      ipdataModel = ipdataService.ipdata("8.8.8.8");
      actual = TEST_CONTEXT.mapper().writeValueAsString(ipdataModel);
      TEST_CONTEXT.assertEqualJson(actual, expected, TEST_CONTEXT.configuration().whenIgnoringPaths("time_zone.current_time"));
    }
  }


  @SneakyThrows
  @Test
  public void testSingleFields() {
    String field = ipdataService.getCountryName("8.8.8.8");
    String expected = TEST_CONTEXT.get("/8.8.8.8/country_name", null);
    Assert.assertEquals(field, expected);
  }


  @SneakyThrows
  @Test(expected = IpdataException.class)
  public void testError() {
    URL url = new URL("https://api.ipdata.co");
    IpdataService serviceWithInvalidKey = Ipdata.builder().url(url)
      .key("THIS_IS_AN_INVALID_KEY")
      .withDefaultCache()
      .feignClient(new ApacheHttpClient(HttpClientBuilder.create()
        .setSSLHostnameVerifier(new NoopHostnameVerifier()).setConnectionTimeToLive(10, TimeUnit.SECONDS)
        .build())).get();
    serviceWithInvalidKey.ipdata("8.8.8.8");
  }

  @Parameters
  public static Iterable<IpdataService> data() {
    return Arrays.asList(TEST_CONTEXT.ipdataService(), TEST_CONTEXT.cachingIpdataService());
  }

}
