package io.ipdata.client.service;

import io.ipdata.client.model.Asn;
import io.ipdata.client.model.Carrier;
import io.ipdata.client.model.Currency;
import io.ipdata.client.model.Language;
import io.ipdata.client.model.TimeZone;
import java.util.List;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(of = "name")
public class IpdataField<T> {
  public static final IpdataField<String> IP = new IpdataField<String>("ip", String.class, false);
  public static final IpdataField<Boolean> IS_EU = new IpdataField<Boolean>("is_eu", Boolean.class, false);
  public static final IpdataField<String> CITY = new IpdataField<String>("city", String.class, false);
  public static final IpdataField<String> REGION = new IpdataField<String>("region", String.class, false);
  public static final IpdataField<String> COUNTRY_NAME = new IpdataField<String>("country_name", String.class, false);
  public static final IpdataField<String> COUNTRY_CODE = new IpdataField<String>("country_code", String.class, false);
  public static final IpdataField<String> CONTINENT_CODE = new IpdataField<String>("continent_code", String.class, false);
  public static final IpdataField<Double> LATITUDE = new IpdataField<Double>("latitude", Double.class, false);
  public static final IpdataField<Double> LONGITUDE = new IpdataField<Double>("longitude", Double.class, false);
  public static final IpdataField<Asn> ASN = new IpdataField<Asn>("asn", Asn.class, false);
  public static final IpdataField<String> ORGANISATION = new IpdataField<String>("organisation", String.class, false);
  public static final IpdataField<String> POSTAL = new IpdataField<String>("postal", String.class, false);
  public static final IpdataField<String> CALLING_CODE = new IpdataField<String>("calling_code", String.class, false);
  public static final IpdataField<String> FLAG = new IpdataField<String>("flag", String.class, false);
  public static final IpdataField<String> EMOJI_FLAG = new IpdataField<String>("emoji_flag", String.class, false);
  public static final IpdataField<String> EMOJI_UNICODE = new IpdataField<String>("emoji_unicode", String.class, false);
  public static final IpdataField<Carrier> CARRIER = new IpdataField<Carrier>("carrier", Carrier.class, false);
  public static final IpdataField<List<Language>> LANGUAGES = new IpdataField<List<Language>>("languages", Language.class, false);
  public static final IpdataField<Currency> CURRENCY = new IpdataField<Currency>("currency", Currency.class, false);
  public static final IpdataField<TimeZone> TIME_ZONE = new IpdataField<TimeZone>("time_zone", TimeZone.class, false);
  public static final IpdataField<TimeZone> THREAT = new IpdataField<TimeZone>("threat", TimeZone.class, false);
  public static final IpdataField<Integer> COUNT = new IpdataField<Integer>("count", Integer.class, false);
  private final String name;
  private final Class<?> type;
  private final boolean isCollection;

  @Override
  public String toString() {
    return name;
  }

  public static class IpdataFieldComparator implements java.util.Comparator<IpdataField<?>> {
    @Override
    public int compare(IpdataField o1, IpdataField o2) {
      if (o1 == null && o2 == null) {
        return Integer.MAX_VALUE;
      }
      if (o1 == null) {
        return o2.name == null ? 0 : Integer.MIN_VALUE;
      }
      if (o2 == null) {
        return o1.name == null ? 0 : Integer.MAX_VALUE;
      }
      return o1.name.compareTo(o2.name);
    }
  }

}
