package com.example;

import com.example.service.SomeOtherService;
import com.example.service.SomeService;
import no.breaks.iocify.Iocify;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ExampleApplicationTest {

    @BeforeAll
    public static void setup(){
        Iocify.run(SomeService.class);
    }

    @Test
    public void getInstanceOfServiceWithAnnotation(){
        SomeService service = Iocify.getService(SomeService.class);
        int result = service.addNumbers(1, 2);
        assertThat(service).isNotNull();
        assertThat(result).isEqualTo(3);
    }
    @Test
    public void unableToCreateInstanceServiceWithoutAnnotation(){
        SomeOtherService service = Iocify.getService(SomeOtherService.class);
        assertThat(service).isNull();
    }
}