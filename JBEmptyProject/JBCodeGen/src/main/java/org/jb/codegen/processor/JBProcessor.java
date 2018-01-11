package org.jb.codegen.processor;

import java.util.Collection;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;


import org.jb.ui.annotation.domain.JBEntity;
import org.jb.ui.annotation.domain.JBEnumeration;
import org.jb.codegen.dictionary.data.DescriptionDictionary;
import org.jb.common.dictionary.project.ProjectConfiguration;
import org.jb.common.config.enums.MappingType;
import org.jb.codegen.reader.compile.configuration.XMLConfigurarionReader;
import org.jb.codegen.reader.compile.mapping.XMLCodeGenMappingReader;
import org.jb.codegen.reader.compile.mapping.XMLPersistenceMappingReader;
import org.jb.codegen.reader.compile.mapping.XMLStreamMappingReader;
import org.jb.codegen.visitor.adjustor.DescriptionAdjustor;
import org.jb.codegen.visitor.adjustor.XMLDescriptionAdjustor;
import org.jb.codegen.visitor.builder.XMLDescriptionProjectBuilder;
import org.jb.codegen.visitor.printer.DescriptionPrinter;
import org.jb.codegen.visitor.builder.DescriptionProjectBuilder;
import org.jb.codegen.visitor.printer.XMLDescriptionPrinter;
import org.jb.codegen.visitor.visitor.DescriptionVisitor;

@SupportedAnnotationTypes(value={"*"})
@SupportedSourceVersion(value = SourceVersion.RELEASE_7)
public class JBProcessor extends AbstractProcessor {
	private ProcessingEnvironment environment;
	private Filer filer;
	private Messager messager;
	ClassLoader loader;
	int round = 0;

	@Override
	public void init(ProcessingEnvironment env) {
		super.init(env);
		environment = env;
		filer = env.getFiler();
		messager = env.getMessager();
		loader = environment.getClass().getClassLoader();
		round = 0;
	}
	
	@Override
	public boolean process(Set<? extends TypeElement> elements, RoundEnvironment env) {
		DescriptionDictionary descriptionDictionary = new DescriptionDictionary();
		ProjectConfiguration projectConfiguration = descriptionDictionary.getProjectConfiguration();

		new XMLConfigurarionReader(projectConfiguration.getCodeGenConfiguration(), filer)
				.parse("jb-codegen-configuration.xml");
		new XMLConfigurarionReader(projectConfiguration.getPersistenceConfiguration(), filer)
				.parse("jb-persistence-configuration.xml");
		new XMLConfigurarionReader(projectConfiguration.getStreamConfiguration(), filer)
				.parse("jb-stream-configuration.xml");

		if(!projectConfiguration.getCodeGenConfiguration().isCodeGenerationEnabled()) {
			return true;
		}
		round++;

		if(round <= 1) {
			descriptionDictionary.putAllElements((Collection<Element>) env.getRootElements());

			if (projectConfiguration.getCodeGenConfiguration()
					.getMappingType() == MappingType.ANNOTATION) {
				DescriptionVisitor descriptionVisitor =
						new DescriptionVisitor(descriptionDictionary);
				DescriptionPrinter descriptionPrinter =
						new DescriptionPrinter(descriptionDictionary);
				DescriptionAdjustor descriptionAdjustor =
						new DescriptionAdjustor(descriptionDictionary);

				DescriptionProjectBuilder descriptionProjectBuilder =
						new DescriptionProjectBuilder(descriptionDictionary);
				descriptionProjectBuilder.setFiler(filer);
				descriptionProjectBuilder.setMessager(messager);
				descriptionProjectBuilder.setElement(null);

				/* CodeGen mapeado através de anotações*/
				Set<? extends Element> annotatedClass =
						env.getElementsAnnotatedWith(JBEntity.class);
				for (Element e : annotatedClass) {
					descriptionVisitor.visitClass((TypeElement) e);
				}

				Set<? extends Element> annotatedEnums =
						env.getElementsAnnotatedWith(JBEnumeration.class);
				for (Element e : annotatedEnums) {
					descriptionVisitor.visitEnum((TypeElement) e);
				}

				/* Verificando se Persistence e Stream estão mapeados com XML*/
				if (projectConfiguration.getPersistenceConfiguration()
						.getMappingType() == MappingType.XML) {
					new XMLPersistenceMappingReader(descriptionDictionary, filer).parse();
				}
				if (projectConfiguration.getStreamConfiguration()
						.getMappingType() == MappingType.XML) {
					new XMLStreamMappingReader(descriptionDictionary, filer).parse();
				}

				descriptionAdjustor.adjust();
				descriptionPrinter.printAllInformation();
				descriptionProjectBuilder.build();

			} else if (projectConfiguration.getCodeGenConfiguration()
					.getMappingType() == MappingType.XML) {
				XMLDescriptionPrinter descriptionPrinter =
						new XMLDescriptionPrinter(descriptionDictionary);
				XMLDescriptionAdjustor descriptionAdjustor =
						new XMLDescriptionAdjustor(descriptionDictionary);

				XMLDescriptionProjectBuilder descriptionProjectBuilder =
						new XMLDescriptionProjectBuilder(descriptionDictionary);
				descriptionProjectBuilder.setFiler(filer);
				descriptionProjectBuilder.setMessager(messager);
				descriptionProjectBuilder.setElement(null);

				/* CodeGen mapeado através de XML*/
				new XMLCodeGenMappingReader(descriptionDictionary, filer).parse();

				/* Verificando se Persistence e Stream estão mapeados com XML*/
				if (projectConfiguration.getPersistenceConfiguration()
						.getMappingType() == MappingType.XML) {
					new XMLPersistenceMappingReader(descriptionDictionary, filer).parse();
				}
				if (projectConfiguration.getStreamConfiguration()
						.getMappingType() == MappingType.XML) {
					new XMLStreamMappingReader(descriptionDictionary, filer).parse();
				}

				descriptionAdjustor.adjust();
				descriptionPrinter.printAllInformation();
				descriptionProjectBuilder.build();

			} else {
				System.out.println("Nenhum mapeamento foi definido.");
			}
		}

		return true;
	}
}
