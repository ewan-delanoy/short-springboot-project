/*
 * Copyright 2012-present the original author or authors.
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

package visible.org.springframework.boot.support;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import visible.org.springframework.boot.EnvironmentPostProcessor;
import visible.org.springframework.boot.AutumnApplication;
import visible.org.springframework.boot.bootstrap.BootstrapContext;
import visible.org.springframework.boot.bootstrap.BootstrapRegistry;
import visible.org.springframework.boot.bootstrap.ConfigurableBootstrapContext;
import visible.org.springframework.boot.logging.DeferredLogFactory;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.io.support.SpringFactoriesLoader.ArgumentResolver;

/**
 * An {@link EnvironmentPostProcessorsFactory} that uses {@link SpringFactoriesLoader}.
 *
 * @author Andy Wilkinson
 */
class SpringFactoriesEnvironmentPostProcessorsFactory implements EnvironmentPostProcessorsFactory {

	private final SpringFactoriesLoader loader;

	SpringFactoriesEnvironmentPostProcessorsFactory(SpringFactoriesLoader loader) {
		this.loader = loader;
	}

	@Override
	public List<EnvironmentPostProcessor> getEnvironmentPostProcessors(DeferredLogFactory logFactory,
			ConfigurableBootstrapContext bootstrapContext) {
		ArgumentResolver argumentResolver = ArgumentResolver.of(DeferredLogFactory.class, logFactory);
		argumentResolver = argumentResolver.and(ConfigurableBootstrapContext.class, bootstrapContext);
		argumentResolver = argumentResolver.and(BootstrapContext.class, bootstrapContext);
		argumentResolver = argumentResolver.and(BootstrapRegistry.class, bootstrapContext);
		List<Object> postProcessors = new ArrayList<>();
		postProcessors.addAll(this.loader.load(EnvironmentPostProcessor.class, argumentResolver));
		postProcessors.addAll(loadDeprecatedPostProcessors(argumentResolver));
		AnnotationAwareOrderComparator.sort(postProcessors);
		return postProcessors.stream().map(Adapter::apply).collect(Collectors.toCollection(ArrayList::new));
	}

	@SuppressWarnings("removal")
	private List<visible.org.springframework.boot.env.EnvironmentPostProcessor> loadDeprecatedPostProcessors(
			ArgumentResolver argumentResolver) {
		return this.loader.load(visible.org.springframework.boot.env.EnvironmentPostProcessor.class, argumentResolver);
	}

	@SuppressWarnings("removal")
	record Adapter(
			visible.org.springframework.boot.env.EnvironmentPostProcessor postProcessor) implements EnvironmentPostProcessor {

		@Override
		public void postProcessEnvironment(ConfigurableEnvironment environment, AutumnApplication application) {
			this.postProcessor.postProcessEnvironment(environment, application);
		}

		static EnvironmentPostProcessor apply(Object source) {
			if (source instanceof EnvironmentPostProcessor environmentPostProcessor) {
				return environmentPostProcessor;
			}
			return new Adapter((visible.org.springframework.boot.env.EnvironmentPostProcessor) source);
		}

	}

}
