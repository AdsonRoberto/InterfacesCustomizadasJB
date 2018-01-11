package org.jb.codegen.visitor.builder;

import org.jb.codegen.dictionary.domain.AttributeDescription;
import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.domain.EnumDescription;
import org.jb.codegen.dictionary.domain.MethodDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.codegen.generator.project.config.PersistenceConfigGenerator;
import org.jb.codegen.generator.project.config.SecurityConfigGenerator;
import org.jb.codegen.generator.project.resource.manifest.AndroidManifestProjectGenerator;
import org.jb.codegen.generator.project.EntityGenerator;
import org.jb.codegen.generator.project.EnumGenerator;
import org.jb.codegen.generator.project.Generator;
import org.jb.codegen.generator.project.MethodGenerator;
import org.jb.codegen.generator.project.ProjectGenerator;

import org.jb.codegen.generator.project.source.activity.DetailActivityEntityGenerator;
import org.jb.codegen.generator.project.source.activity.EditActivityEntityGenerator;
import org.jb.codegen.generator.project.source.activity.FindActivityEntityGenerator;
import org.jb.codegen.generator.project.source.activity.InsertActivityEntityGenerator;
import org.jb.codegen.generator.project.source.activity.ListActivityEntityGenerator;
import org.jb.codegen.generator.project.source.activity.ListActivityJoinTableGenerator;
import org.jb.codegen.generator.project.source.activity.ListActivityProjectGenerator;
import org.jb.codegen.generator.project.source.delegate.ListFragmentDelegateEntityGenerator;
import org.jb.codegen.generator.project.source.delegate.ListFragmentDelegateProjectGenerator;
import org.jb.codegen.generator.project.source.fragment.ListFragmentEntityGenerator;
import org.jb.codegen.generator.project.source.fragment.ListFragmentProjectGenerator;
import org.jb.codegen.generator.project.resource.layout.DetailActivityLayoutEntityGenerator;
import org.jb.codegen.generator.project.resource.layout.EditActivityLayoutEntityGenerator;
import org.jb.codegen.generator.project.resource.layout.FindActivityLayoutEntityGenerator;
import org.jb.codegen.generator.project.resource.layout.FindResultListActivityLayoutEntityGenerator;
import org.jb.codegen.generator.project.resource.layout.InsertActivityLayoutEntityGenerator;
import org.jb.codegen.generator.project.resource.layout.ListActivityLayoutEntityGenerator;
import org.jb.codegen.generator.project.resource.layout.ListActivityLayoutJoinTableGenerator;
import org.jb.codegen.generator.project.resource.layout.ListFragmentLayoutEntityGenerator;
import org.jb.codegen.generator.project.resource.layout.SelectListActivityLayoutEntityGenerator;
import org.jb.codegen.generator.project.resource.layout.dialog.DateDialogGenerator;
import org.jb.codegen.generator.project.resource.layout.dialog.DialogLayoutMethodGenerator;
import org.jb.codegen.generator.project.resource.layout.dialog.ListActivityInsertDialogLayoutJoinTableGenerator;
import org.jb.codegen.generator.project.resource.layout.row.ListFragmentRowLayoutEntityGenerator;
import org.jb.codegen.generator.project.resource.layout.row.ListFragmentRowLayoutProjectGenerator;
import org.jb.codegen.generator.project.resource.menu.DetailActivityMenuEntityGenerator;
import org.jb.codegen.generator.project.resource.menu.EditActivityMenuEntityGenerator;
import org.jb.codegen.generator.project.resource.menu.FindActivityMenuEntityGenerator;
import org.jb.codegen.generator.project.resource.menu.InsertActivityMenuEntityGenerator;
import org.jb.codegen.generator.project.resource.menu.ListActivityMenuEntityGenerator;
import org.jb.codegen.generator.project.resource.menu.ListActivityMenuJoinTableGenerator;
import org.jb.codegen.generator.project.resource.menu.context.FindResultListActivityContextMenuEntityGenerator;
import org.jb.codegen.generator.project.resource.menu.context.ListActivityContextMenuEntityGenerator;
import org.jb.codegen.generator.project.resource.menu.context.ListActivityContextMenuJoinTableGenerator;
import org.jb.codegen.generator.project.resource.menu.context.SelectListActivityContextMenuEntityGenerator;
import org.jb.codegen.generator.project.resource.values.ResourceDimensProjectGenerator;
import org.jb.codegen.generator.project.resource.values.ResourceStringsProjectGenerator;
import org.jb.codegen.generator.project.resource.values.ResourceStylesProjectGenerator;
import org.jb.persistence.web.annotation.WebAggregation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

/**
 * Created by fabiano on 11/07/17.
 */

public class DescriptionProjectBuilder extends ProjectBuilder {
    public DescriptionProjectBuilder(DescriptionDictionary dictionary) {
        super(dictionary);
    }

    public DescriptionProjectBuilder(DescriptionDictionary dictionary, Messager messager, Filer filer, Element element) {
        super(dictionary, messager, filer, element);
    }

    public void build() {
        buildProjectConfigFiles();
        buildProjectResourceFiles();
        buildEntityResourceFiles();
        buildEnumResourceFiles();
    }

    public void buildProjectResourceFiles() {
        List<ProjectGenerator> listProjectGenerator = new ArrayList<ProjectGenerator>();
        listProjectGenerator.add(new AndroidManifestProjectGenerator(dictionary, messager, filer, element));

        listProjectGenerator.add(new ResourceDimensProjectGenerator(dictionary, messager, filer, element));
        listProjectGenerator.add(new ResourceStringsProjectGenerator(dictionary, messager, filer, element));
        listProjectGenerator.add(new ResourceStylesProjectGenerator(dictionary, messager, filer, element));

        listProjectGenerator.add(new ListFragmentRowLayoutProjectGenerator(dictionary, messager, filer, element));
        listProjectGenerator.add(new ListActivityProjectGenerator(dictionary, messager, filer, element));
        listProjectGenerator.add(new ListFragmentProjectGenerator(dictionary, messager, filer, element));
        listProjectGenerator.add(new ListFragmentDelegateProjectGenerator(dictionary, messager, filer, element));

        for(int i = 0; i < listProjectGenerator.size(); i++) {
            ProjectGenerator pg = listProjectGenerator.get(i);
            pg.source();
            pg.generate();
        }
        listProjectGenerator.clear();
    }

    public void buildProjectConfigFiles() {
        List<Generator> listGenerator = new ArrayList<Generator>();
        listGenerator.add(new DateDialogGenerator(messager, filer, element));
        listGenerator.add(new PersistenceConfigGenerator(messager, filer, element));
        listGenerator.add(new SecurityConfigGenerator(messager, filer, element));

        for(int i = 0; i < listGenerator.size(); i++) {
            Generator g = listGenerator.get(i);
            g.source();
            g.generate();
        }
        listGenerator.clear();
    }

    public void buildEntityResourceFiles() {
        List<MethodGenerator> listMethodGenerator = new ArrayList<MethodGenerator>();

        List<EntityGenerator> listEntityGenerator = new ArrayList<EntityGenerator>();
        for(ClassDescription cd : dictionary.getClassValues()) {
            listEntityGenerator.add(new ListActivityMenuEntityGenerator(cd, dictionary, messager, filer, element));
            listEntityGenerator.add(new ListActivityContextMenuEntityGenerator(cd, dictionary, messager, filer, element));
            listEntityGenerator.add(new ListActivityLayoutEntityGenerator(cd, dictionary, messager, filer, element));
            listEntityGenerator.add(new ListActivityEntityGenerator(cd, dictionary, messager, filer, element));

            listEntityGenerator.add(new ListFragmentLayoutEntityGenerator(cd, dictionary, messager, filer, element));
            listEntityGenerator.add(new ListFragmentDelegateEntityGenerator(cd, dictionary, messager, filer, element));
            listEntityGenerator.add(new ListFragmentRowLayoutEntityGenerator(cd, dictionary, messager, filer, element));
            listEntityGenerator.add(new ListFragmentEntityGenerator(cd, dictionary, messager, filer, element));

            if(!cd.containsModifier(Modifier.ABSTRACT.name())) {
                listEntityGenerator.add(new InsertActivityLayoutEntityGenerator(cd, dictionary, messager, filer, element));
                listEntityGenerator.add(new InsertActivityMenuEntityGenerator(cd, dictionary, messager, filer, element));
                listEntityGenerator.add(new InsertActivityEntityGenerator(cd, dictionary, messager, filer, element));

                listEntityGenerator.add(new DetailActivityLayoutEntityGenerator(cd, dictionary, messager, filer, element));
                listEntityGenerator.add(new DetailActivityMenuEntityGenerator(cd, dictionary, messager, filer, element));
                listEntityGenerator.add(new DetailActivityEntityGenerator(cd, dictionary, messager, filer, element));

                listEntityGenerator.add(new EditActivityLayoutEntityGenerator(cd, dictionary, messager, filer, element));
                listEntityGenerator.add(new EditActivityMenuEntityGenerator(cd, dictionary, messager, filer, element));
                listEntityGenerator.add(new EditActivityEntityGenerator(cd, dictionary, messager, filer, element));
            }

            listEntityGenerator.add(new FindActivityLayoutEntityGenerator(cd, dictionary, messager, filer, element));
            listEntityGenerator.add(new FindActivityMenuEntityGenerator(cd, dictionary, messager, filer, element));
            listEntityGenerator.add(new FindActivityEntityGenerator(cd, dictionary, messager, filer, element));

            listEntityGenerator.add(new FindResultListActivityContextMenuEntityGenerator(cd, dictionary, messager, filer, element));
            listEntityGenerator.add(new FindResultListActivityLayoutEntityGenerator(cd, dictionary, messager, filer, element));

            listEntityGenerator.add(new SelectListActivityContextMenuEntityGenerator(cd, dictionary, messager, filer, element));
            listEntityGenerator.add(new SelectListActivityLayoutEntityGenerator(cd, dictionary, messager, filer, element));

            for(MethodDescription md : cd.getMethodsForInterface()) {
                if(!md.getParameterDescriptions().isEmpty()) {
                    listMethodGenerator.add(new DialogLayoutMethodGenerator(md, dictionary, messager, filer, element));
                }
            }

            for(AttributeDescription ad : cd.getAttributesForInterface()) {
                if(ad.isCollection() && ad.isAnnotatedWith(WebAggregation.class)) {
                    if(dictionary.containsClassWithKey(ad.getCollectionType())) {
                        ClassDescription collectionClass = dictionary.getClass(ad.getCollectionType());

                        //ListActivity
                        listEntityGenerator.add(new ListActivityJoinTableGenerator(cd, ad, dictionary, messager, filer, element));
                        //Layout ListActivity
                        listEntityGenerator.add(new ListActivityLayoutJoinTableGenerator(cd, ad, dictionary, messager, filer, element));
                        //Context Menu ListActivity
                        listEntityGenerator.add(new ListActivityContextMenuJoinTableGenerator(cd, ad, dictionary, messager, filer, element));
                        //Menu ListActivity
                        listEntityGenerator.add(new ListActivityMenuJoinTableGenerator(cd, ad, dictionary, messager, filer, element));
                        //Insert Dialog
                        listEntityGenerator.add(new ListActivityInsertDialogLayoutJoinTableGenerator(cd, ad, dictionary, messager, filer, element));
                    }
                }
            }
        }

        for(int i = 0; i < listEntityGenerator.size(); i++) {
            EntityGenerator eg = listEntityGenerator.get(i);
            eg.source();
            eg.generate();
        }
        listEntityGenerator.clear();

        for(int i = 0; i < listMethodGenerator.size(); i++) {
            MethodGenerator mg = listMethodGenerator.get(i);
            mg.source();
            mg.generate();
        }
        listMethodGenerator.clear();
    }

    public void buildEnumResourceFiles() {
        List<EnumGenerator> listEnumGenerator = new ArrayList<EnumGenerator>();
        for(EnumDescription ed : dictionary.getEnumValues()) {
            /** Nothing to Insert */
        }

        for(int i = 0; i < listEnumGenerator.size(); i++) {
            EnumGenerator eg = listEnumGenerator.get(i);
            eg.source();
            eg.generate();
        }
        listEnumGenerator.clear();
    }
}
