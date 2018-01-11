package org.jb.codegen.generator.project.source.delegate;

import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.codegen.generator.auxiliar.FragmentResourceName;
import org.jb.codegen.generator.project.EntityGenerator;
import org.jb.codegen.generator.auxiliar.Operation;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.JavaFileObject;

public class ListFragmentDelegateEntityGenerator extends EntityGenerator {

	public ListFragmentDelegateEntityGenerator(ClassDescription c, DescriptionDictionary dictionary) {
		super(c, dictionary);
		// TODO Auto-generated constructor stub
	}

	public ListFragmentDelegateEntityGenerator(ClassDescription c, DescriptionDictionary dictionary,
											   Messager messager, Filer filer, Element element) {
		super(c, dictionary, messager, filer, element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void source() {
		FragmentResourceName fragmentResourceName = new FragmentResourceName();

		str.append("package app.jb.generated;\n");
		str.append("\n");
		str.append("import java.util.List;\n");
		str.append("import " + c.getCanonicalName() + ";\n");
		str.append("\n");
		str.append("public interface " + fragmentResourceName.getClassDelegateName(c, Operation.LIST, dictionary) + " {\n");
		str.append("    List<" + c.getSimpleName() + "> getList" + c.getSimpleName() + "();\n");
		str.append("}\n");
	}

	@Override
	public void generate() {
		FragmentResourceName fragmentResourceName = new FragmentResourceName();
		JavaFileObject file = null;
		try {
			file = filer.createSourceFile("java." + "app.jb.generated." + fragmentResourceName.getClassDelegateName(c, Operation.LIST, dictionary), element);
			file.openWriter().append(str.toString()).close();
		} catch (FilerException e2) {
			//e2.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
