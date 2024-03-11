/*
 * Copyright 2012-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.docker.compose.service.connection.postgres;

import org.junit.jupiter.api.Test;

import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.boot.docker.compose.service.connection.test.AbstractDockerComposeIntegrationTests;
import org.springframework.boot.testsupport.testcontainers.BitnamiImageNames;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link PostgresJdbcDockerComposeConnectionDetailsFactory}.
 *
 * @author Scott Frederick
 */
class PostgresBitnamiJdbcDockerComposeConnectionDetailsFactoryIntegrationTests
		extends AbstractDockerComposeIntegrationTests {

	PostgresBitnamiJdbcDockerComposeConnectionDetailsFactoryIntegrationTests() {
		super("postgres-bitnami-compose.yaml", BitnamiImageNames.postgresql());
	}

	@Test
	void runCreatesConnectionDetails() {
		JdbcConnectionDetails connectionDetails = run(JdbcConnectionDetails.class);
		assertThat(connectionDetails.getUsername()).isEqualTo("myuser");
		assertThat(connectionDetails.getPassword()).isEqualTo("secret");
		assertThat(connectionDetails.getJdbcUrl()).startsWith("jdbc:postgresql://").endsWith("/mydatabase");
	}

}