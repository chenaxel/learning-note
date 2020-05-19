package com.axel.jvm.annotations;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner6;
import java.util.EnumSet;

import static javax.tools.Diagnostic.Kind.WARNING;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/4/24
 */
public class NameChecker {

	private final Messager messager;
	NameCheckScanner nameCheckScanner = new NameCheckScanner();

	NameChecker(ProcessingEnvironment processingEnv) {
		messager = processingEnv.getMessager();
	}

	public void checkNames(Element e) {
		nameCheckScanner.scan(e);
	}

	private class NameCheckScanner extends ElementScanner6<Void, Void> {

		@Override
		public Void visitType(TypeElement e, Void p) {
			scan(e.getTypeParameters(), p);
			checkCamelCase(e, true);
			super.visitType(e, p);
			return null;
		}

		@Override
		public Void visitExecutable(ExecutableElement e, Void p) {
			if (e.getKind() == ElementKind.METHOD) {
				Name name = e.getSimpleName();
				if (name.contentEquals(e.getEnclosingElement().getSimpleName())) {
					messager.printMessage(WARNING, "一个普通方法" + name + "不应当与类名重复,避免构造函数产生混淆");
					checkCamelCase(e, true);
				}
			}
			super.visitExecutable(e, p);
			return null;
		}

		private boolean heuristicallyConstant(VariableElement e) {
			if (e.getEnclosingElement().getKind() == ElementKind.INTERFACE) {
				return true;
			} else
				return e.getKind() == ElementKind.FIELD && e.getModifiers().containsAll(EnumSet.of(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL));
		}

		@Override
		public Void visitTypeParameter(TypeParameterElement e, Void aVoid) {
			return super.visitTypeParameter(e, aVoid);
		}

		private void checkCamelCase(Element e, boolean initialCaps) {
			String name = e.getSimpleName().toString();
			boolean previousUpper = false;
			boolean conventional = true;
			int firstCodePoint = name.codePointAt(0);
			if (Character.isUpperCase(firstCodePoint)) {
				previousUpper = true;
				if (!initialCaps) {
					messager.printMessage(WARNING, "名称" + name + "应当以小写字母开头", e);
					return;
				}
			} else if (Character.isLowerCase(firstCodePoint)) {
				if (initialCaps) {
					messager.printMessage(WARNING, "名称" + name + "应当以大写字母开头", e);
				}
			} else {
				conventional = false;
			}
			if (conventional) {
				int cp = firstCodePoint;
				for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
					cp = name.codePointAt(i);
					if (Character.isUpperCase(cp)) {
						if (previousUpper) {
							conventional = false;
							break;
						}
						previousUpper = true;
					} else {
						previousUpper = false;
					}
				}
			}
			if (!conventional) {
				messager.printMessage(WARNING, "名称" + name + "应当符合驼式命名法（Camel Case Names）", e);
			}
		}
	}
}
