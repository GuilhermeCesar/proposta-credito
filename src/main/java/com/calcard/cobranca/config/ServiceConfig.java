package com.calcard.cobranca.config;

import com.calcard.cobranca.model.Proposal;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
@EntityScan(basePackageClasses = Proposal.class)
public class ServiceConfig {
}
