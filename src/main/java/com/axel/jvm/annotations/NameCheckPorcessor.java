package com.axel.jvm.annotations;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/4/24
 */
public class NameCheckPorcessor extends AbstractProcessor {
	private NameChecker nameChecker;

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		nameChecker = new NameChecker(processingEnv);
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		if (!roundEnv.processingOver()) {
			for (Element rootElement : roundEnv.getRootElements()) {
				nameChecker.checkNames(rootElement);
			}
		}
		return false;
	}
}