package org.jb.codegen.generator.project.resource.values;

import org.jb.codegen.generator.auxiliar.DialogAction;
import org.jb.codegen.generator.auxiliar.TypedElementAction;
import org.jb.ui.annotation.domain.JBAction;
import org.jb.ui.annotation.domain.JBAttribute;
import org.jb.ui.annotation.domain.JBEntity;
import org.jb.ui.annotation.domain.JBParameter;
import org.jb.codegen.dictionary.domain.AttributeDescription;
import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.domain.MethodDescription;
import org.jb.codegen.dictionary.domain.ParameterDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.common.config.enums.MappingType;
import org.jb.codegen.generator.auxiliar.ActivityResourceName;
import org.jb.codegen.generator.auxiliar.MenuAction;
import org.jb.codegen.generator.auxiliar.Operation;

import org.jb.codegen.generator.project.ProjectGenerator;
import org.jb.codegen.reader.XMLReader;
import org.jb.ui.xml.domain.XMLJBAction;
import org.jb.ui.xml.domain.XMLJBAttribute;
import org.jb.ui.xml.domain.XMLJBEntity;
import org.jb.ui.xml.domain.XMLJBParameter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

public class ResourceStringsProjectGenerator extends ProjectGenerator {

	public ResourceStringsProjectGenerator(DescriptionDictionary dictionary) {
		super(dictionary);
		// TODO Auto-generated constructor stub
	}

	public ResourceStringsProjectGenerator(DescriptionDictionary dictionary,
			Messager messager, Filer filer, Element element) {
		super(dictionary, messager, filer, element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void source() {
		ActivityResourceName activityResourceName = new ActivityResourceName();

		List<String[]> list = new XMLReader(filer).getValues("strings.xml", "string");
		
		List<String> names = new ArrayList<String>();
		//names.add("action_new");
		names.add(activityResourceName.getClassMenuActionStringResourceName(MenuAction.NEW));
		//names.add("action_detail");
		names.add(activityResourceName.getClassMenuActionStringResourceName(MenuAction.DETAIL));
		//names.add("action_edit");
		names.add(activityResourceName.getClassMenuActionStringResourceName(MenuAction.EDIT));
		//names.add("action_delete");
		names.add(activityResourceName.getClassMenuActionStringResourceName(MenuAction.DELETE));
		//names.add("action_find");
		names.add(activityResourceName.getClassMenuActionStringResourceName(MenuAction.FIND));
		//names.add("action_select");
		names.add(activityResourceName.getClassMenuActionStringResourceName(MenuAction.SELECT));
		//names.add("action_insert_save");
		names.add(activityResourceName.getClassMenuActionStringResourceName(MenuAction.SAVE));
		//names.add("action_edit_save");
		//names.add(activityResourceName.getClassMenuActionStringResourceName(MenuAction.SAVE));
		//names.add("action_add");
		names.add(activityResourceName.getClassMenuActionStringResourceName(MenuAction.ADD));
			
		List<String> values = new ArrayList<String>();
		values.add("Novo");
		values.add("Detalhes");
		values.add("Editar");
		values.add("Excluir");
		values.add("Buscar");
		values.add("Selecionar");
		values.add("Salvar");
		//values.add("Salvar");
		values.add("Adicionar");

		names.add(activityResourceName.getClassDialogActionStringResourceName(DialogAction.YES));
		names.add(activityResourceName.getClassDialogActionStringResourceName(DialogAction.NO));
		names.add(activityResourceName.getClassDialogActionStringResourceName(DialogAction.CANCEL));
		names.add(activityResourceName.getClassDialogActionStringResourceName(DialogAction.OK));

		values.add("Sim");
		values.add("Não");
		values.add("Cancelar");
		values.add("Ok");
		
		//names.add("action_select_object");
		names.add(activityResourceName.getClassElementWidgetActionStringResourceName(TypedElementAction.SELECT));
		//names.add("action_detail_object");
		names.add(activityResourceName.getClassElementWidgetActionStringResourceName(TypedElementAction.DETAIL));
		//names.add("action_show_collection");
		names.add(activityResourceName.getClassElementWidgetActionStringResourceName(TypedElementAction.COLLECTION));
		values.add("SELECIONE");
		values.add("DETALHE");
		values.add("COLEÇÃO");
		
		for(ClassDescription c : dictionary.getClassValues()) {
			if(c.isAnnotatedWith(JBEntity.class)
					|| c.isXmlAnnotatedWith(XMLJBEntity.class)) {

				XMLJBEntity entity = null;
				if(dictionary.getProjectConfiguration().getPersistenceConfiguration()
						.getMappingType() == MappingType.ANNOTATION) {
					entity = XMLJBEntity.adapt((JBEntity) c.findAnnotationByType(JBEntity.class));
				}
				else if(dictionary.getProjectConfiguration().getPersistenceConfiguration()
						.getMappingType() == MappingType.XML) {
					entity = (XMLJBEntity) c.findXmlAnnotationByType(XMLJBEntity.class);
				}
				else {
					//DO NOTHING
				}

				if(entity != null) {
					names.add(activityResourceName.getClassTitleStringResourceName(c, Operation.LIST, dictionary));
					//names.add("activity_list_title_" + c.getName().toLowerCase());
					values.add(entity.getCollectionLabel());

					names.add(activityResourceName.getClassTitleStringResourceName(c, Operation.INSERT, dictionary));
					//names.add("activity_insert_title_" + c.getName().toLowerCase());
					values.add("Cadastrar " + entity.getLabel());

					names.add(activityResourceName.getClassTitleStringResourceName(c, Operation.EDIT, dictionary));
					//names.add("activity_edit_title_" + c.getName().toLowerCase());
					values.add("Editar " + entity.getLabel());

					names.add(activityResourceName.getClassTitleStringResourceName(c, Operation.DETAIL, dictionary));
					//names.add("activity_detail_title_" + c.getName().toLowerCase());
					values.add(entity.getLabel());

					names.add(activityResourceName.getClassTitleStringResourceName(c, Operation.FIND, dictionary));
					//names.add("activity_find_title_" + c.getName().toLowerCase());
					values.add("Buscar " + entity.getLabel());

					names.add(activityResourceName.getClassTitleStringResourceName(c, Operation.SELECT_LIST, dictionary));
					//names.add("activity_select_list_title_" + c.getName().toLowerCase());
					values.add("Selecionar " + entity.getLabel());

					names.add(activityResourceName.getClassTitleStringResourceName(c, Operation.FIND_RESULT_LIST, dictionary));
					//names.add("activity_find_result_list_title_" + c.getName().toLowerCase());
					values.add("Resultado em " + entity.getCollectionLabel());
				}
			}
		}
		
		for(ClassDescription c : dictionary.getClassValues()) {
			for(MethodDescription met : c.getAllMethodDescriptions()) {

				if(met.isAnnotatedWith(JBAction.class)
						|| met.isXmlAnnotatedWith(XMLJBAction.class)) {

					XMLJBAction actionAnnot = null;
					if (dictionary.getProjectConfiguration().getCodeGenConfiguration()
							.getMappingType() == MappingType.ANNOTATION) {
						actionAnnot = XMLJBAction.adapt((JBAction) met.findAnnotationByType(JBAction.class));
					} else if (dictionary.getProjectConfiguration().getCodeGenConfiguration()
							.getMappingType() == MappingType.XML) {
						actionAnnot = (XMLJBAction) met.findXmlAnnotationByType(XMLJBAction.class);
					} else {
						//DO NOTHING
					}

					names.add("action_label_class_" + c.getSimpleName().toLowerCase() + "_method_" + met.getName().toLowerCase());
					if (actionAnnot != null) {
						values.add(actionAnnot.getName());
					} else {
						values.add(met.getName());
					}

					if (!met.getParameterDescriptions().isEmpty()) {
						for (ParameterDescription param : met.getParameterDescriptions()) {
							if(param.isAnnotatedWith(JBParameter.class)
									|| param.isXmlAnnotatedWith(XMLJBParameter.class)) {

								XMLJBParameter paramAnnot = null;
								if (dictionary.getProjectConfiguration().getCodeGenConfiguration()
										.getMappingType() == MappingType.ANNOTATION) {
									paramAnnot = XMLJBParameter.adapt((JBParameter) param.findAnnotationByType(JBParameter.class));
								} else if (dictionary.getProjectConfiguration().getCodeGenConfiguration()
										.getMappingType() == MappingType.XML) {
									paramAnnot = (XMLJBParameter) param.findXmlAnnotationByType(XMLJBParameter.class);
								} else {
									//DO NOTHING
								}

								names.add("action_label_class_" + c.getSimpleName().toLowerCase() + "_method_" + param.getMethodDescription().getName().toLowerCase() + "_parameter_" + param.getName().toLowerCase());
								if (paramAnnot != null) {
									values.add(paramAnnot.getName());
								} else {
									values.add(param.getName());
								}
							}
						}
					}
				}
			}
			for(AttributeDescription attr : c.getAllAttributeDescriptions()) {
				names.add("action_label_class_" + c.getSimpleName().toLowerCase() + "_attribute_" + attr.getName().toLowerCase());

				XMLJBAttribute attrAnnot = null;
				if (dictionary.getProjectConfiguration().getCodeGenConfiguration()
						.getMappingType() == MappingType.ANNOTATION) {
					attrAnnot = XMLJBAttribute.adapt((JBAttribute) attr.findAnnotationByType(JBAttribute.class));
				} else if (dictionary.getProjectConfiguration().getCodeGenConfiguration()
						.getMappingType() == MappingType.XML) {
					attrAnnot = (XMLJBAttribute) attr.findXmlAnnotationByType(XMLJBAttribute.class);
				} else {
					//DO NOTHING
				}

				if(attrAnnot != null) {
					values.add(attrAnnot.getName().toUpperCase());
				}
				else {
					values.add(attr.getName().toUpperCase());
				}
			}
		}

		for(ClassDescription c : dictionary.getClassValues()) {
			for(AttributeDescription jt : c.getAttributesForInterface()) {
				if((jt.isAnnotatedWith(JBAttribute.class) || jt.isXmlAnnotatedWith(XMLJBAttribute.class))
						&& jt.isCollection()) {
					ClassDescription target = dictionary.getClass(jt.getCollectionType());

					XMLJBEntity targetEntityAnnot = null;
					if(dictionary.getProjectConfiguration().getCodeGenConfiguration()
							.getMappingType() == MappingType.ANNOTATION) {
						targetEntityAnnot = XMLJBEntity.adapt((JBEntity) target.findAnnotationByType(JBEntity.class));
					}
					else if(dictionary.getProjectConfiguration().getCodeGenConfiguration()
							.getMappingType() == MappingType.XML) {
						targetEntityAnnot = (XMLJBEntity) target.findXmlAnnotationByType(XMLJBEntity.class);
					}
					else {
						//DO NOTHING
					}

					if(targetEntityAnnot != null) {
						names.add(activityResourceName.getClassTitleStringResourceName(c, target, Operation.LIST, dictionary));
						values.add(targetEntityAnnot.getCollectionLabel());

						names.add(activityResourceName.getJoinTableClassInsertDialogTitleStringResourceName(c, target, Operation.LIST, dictionary));
						values.add("Adicionar " + targetEntityAnnot.getCollectionLabel());

						names.add(activityResourceName.getJoinTableClassRemoveDialogTitleStringResourceName(c, target, Operation.LIST, dictionary));
						values.add("Remover " + targetEntityAnnot.getCollectionLabel());
					}
				}
			}
		}
		
		if(list == null || list.isEmpty()) {
			names.add("app_name");		//activityResourceName.getAppStringName();
			values.add("Project");
		}
		else {
			for (int i = 0; i < list.size(); i++) {
				String[] s = list.get(i);
				if(!names.contains(s[0])) {
					names.add(s[0]);
					values.add(s[1]);
				}
			}
		}
		
		if(!names.contains("app_name")) {
			names.add(0, "app_name");	//activityResourceName.getAppStringName();
			values.add(0, "Project");
		}

		str.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		str.append("<resources>\n");
		for (int i = 0; i < names.size(); i++) {
			str.append("	<string name=\"" + names.get(i) + "\">" + values.get(i) + "</string>\n");
		}
		str.append("</resources>");
	}

	@Override
	public void generate() {
		FileObject file = null;
		try {
			file = filer.createResource(StandardLocation.SOURCE_OUTPUT , "res.values", "strings.xml", element);
			BufferedWriter bw = new BufferedWriter(file.openWriter());
			bw.append(str.toString());
			bw.close();
			//file.openWriter().append(str.toString()).close();
		} catch (FilerException e2) {
			//e2.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
