package grisbiweb.server.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.SneakyThrows;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public final class TestHelper {

    public static final PodamFactory FACTORY = new PodamFactoryImpl();

    public static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public static <T> T manufacture(Class<T> pojo) {
        return FACTORY.manufacturePojo(pojo);
    }

    @SneakyThrows
    public static String serialize(Object object) {
        return MAPPER.writeValueAsString(object);
    }
}
