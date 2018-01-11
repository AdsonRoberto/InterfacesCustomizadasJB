package org.jb.codegen.generator.project.resource.manifest;

import org.jb.codegen.dictionary.domain.AttributeDescription;
import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.codegen.generator.auxiliar.ActivityResourceName;
import org.jb.codegen.generator.project.ProjectGenerator;
import org.jb.codegen.generator.auxiliar.Operation;

import org.jb.codegen.reader.AndroidManifestXMLReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

public class AndroidManifestProjectGenerator extends ProjectGenerator {
	boolean sucess = false;
	
	public AndroidManifestProjectGenerator(DescriptionDictionary dictionary) {
		super(dictionary);
		// TODO Auto-generated constructor stub
	}

	public AndroidManifestProjectGenerator(DescriptionDictionary dictionary,
			Messager messager, Filer filer, Element element) {
		super(dictionary, messager, filer, element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void source() {
		StringBuilder strBefore = new StringBuilder();
		StringBuilder strMiddle = new StringBuilder();
		StringBuilder strAfter = new StringBuilder();

		ActivityResourceName activityResourceName = new ActivityResourceName();
		FileObject file = null;
		try {
			file = filer.getResource(StandardLocation.SOURCE_OUTPUT, "", "AndroidManifest.xml");

			if(file == null) {
				strBefore.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
				strBefore.append("<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"\n");
				strBefore.append("	package=\"br.com.app.business\">\n");
				strBefore.append("\n");

				strBefore.append("<uses-permission android:name=\"android.permission.INTERNET\" />\n");
				strBefore.append("\n");

				strMiddle.append("	<application\n");
				strMiddle.append("		android:allowBackup=\"true\"\n");
				strMiddle.append("		android:icon=\"@drawable/ic_launcher\"\n");
				strMiddle.append("		android:label=\"@string/app_name\"\n");
				strMiddle.append("		android:theme=\"@style/AppTheme\" >\n");
				strMiddle.append("\n");
				strMiddle.append("		<activity\n");
				strMiddle.append("			android:name=\"app.jb.generated.ProjectListActivity\"\n");
				strMiddle.append("			android:label=\"@string/app_name\" >\n");
				strMiddle.append("			<intent-filter>\n");
				strMiddle.append("				<action android:name=\"android.intent.action.MAIN\" />\n");
				strMiddle.append("\n");
				strMiddle.append("				<category android:name=\"android.intent.category.LAUNCHER\" />\n");
				strMiddle.append("			</intent-filter>\n");
				strMiddle.append("		</activity>\n");

				strMiddle.append("\n");

				if(dictionary.getTotalClasses() > 0) {
					for(ClassDescription c : dictionary.getClassValues()) {
						strMiddle.append("		<activity\n");
						strMiddle.append("			android:name=\"app.jb.generated." + activityResourceName.getClassName(c, Operation.INSERT, dictionary) + "\"\n");
						strMiddle.append("			android:label=\"@string/app_name\" >\n");
						strMiddle.append("		</activity>\n");
						strMiddle.append("		<activity\n");
						strMiddle.append("			android:name=\"app.jb.generated." + activityResourceName.getClassName(c, Operation.DETAIL, dictionary) + "\"\n");
						strMiddle.append("			android:label=\"@string/app_name\" >\n");
						strMiddle.append("		</activity>\n");
						strMiddle.append("		<activity\n");
						strMiddle.append("			android:name=\"app.jb.generated." + activityResourceName.getClassName(c, Operation.EDIT, dictionary) + "\"\n");
						strMiddle.append("			android:label=\"@string/app_name\" >\n");
						strMiddle.append("		</activity>\n");
						strMiddle.append("		<activity\n");
						strMiddle.append("			android:name=\"app.jb.generated." + activityResourceName.getClassName(c, Operation.FIND, dictionary) + "\"\n");
						strMiddle.append("			android:label=\"@string/app_name\" >\n");
						strMiddle.append("		</activity>\n");
						strMiddle.append("		<activity\n");
						strMiddle.append("			android:name=\"app.jb.generated." + activityResourceName.getClassName(c, Operation.LIST, dictionary) + "\"\n");
						strMiddle.append("			android:label=\"@string/app_name\" >\n");
						strMiddle.append("		</activity>\n");

						for(AttributeDescription ad : c.getAttributesForInterface()) {
							if (ad.isCollection()) {
								if (dictionary.containsClassWithKey(ad.getCollectionType())) {
									strMiddle.append("		<activity\n");
									strMiddle.append("			android:name=\"app.jb.generated." + activityResourceName.getClassName(c, ad, Operation.LIST, dictionary) + "\"\n");
									strMiddle.append("			android:label=\"@string/app_name\" >\n");
									strMiddle.append("		</activity>\n");
								}
							}
						}
					}
				}
				strMiddle.append("	</application>\n");

				strAfter.append("\n");
				strAfter.append("</manifest>");
			}
			else {
				BufferedReader br = new BufferedReader(file.openReader(true));
				AndroidManifestXMLReader reader = AndroidManifestXMLReader.getInstance(this.filer);

				if(reader.hasElement("application")) {
					String line = br.readLine();
					while(!line.contains("<application")) {
						strBefore.append(line + "\n");
						line = br.readLine();
					}
					while(!line.contains(">")) {
						//messager.printMessage(Kind.WARNING, line, element);
						strBefore.append(line + "\n");
						line = br.readLine();
					}
					strBefore.append(line + "\n");

					strMiddle.append("		<activity\n");
					strMiddle.append("			android:name=\"app.jb.generated.ProjectListActivity\"\n");
					strMiddle.append("			android:label=\"@string/app_name\" >\n");
					strMiddle.append("			<intent-filter>\n");
					strMiddle.append("				<action android:name=\"android.intent.action.MAIN\" />\n");
					strMiddle.append("\n");
					strMiddle.append("				<category android:name=\"android.intent.category.LAUNCHER\" />\n");
					strMiddle.append("			</intent-filter>\n");
					strMiddle.append("		</activity>\n");

					strMiddle.append("\n");

					if(dictionary.getTotalClasses() > 0) {
						for(ClassDescription c : dictionary.getClassValues()) {
							strMiddle.append("		<activity\n");
							strMiddle.append("			android:name=\"app.jb.generated." + activityResourceName.getClassName(c, Operation.INSERT, dictionary) + "\"\n");
							strMiddle.append("			android:label=\"@string/app_name\" >\n");
							strMiddle.append("		</activity>\n");
							strMiddle.append("		<activity\n");
							strMiddle.append("			android:name=\"app.jb.generated." + activityResourceName.getClassName(c, Operation.DETAIL, dictionary) + "\"\n");
							strMiddle.append("			android:label=\"@string/app_name\" >\n");
							strMiddle.append("		</activity>\n");
							strMiddle.append("		<activity\n");
							strMiddle.append("			android:name=\"app.jb.generated." + activityResourceName.getClassName(c, Operation.EDIT, dictionary) + "\"\n");
							strMiddle.append("			android:label=\"@string/app_name\" >\n");
							strMiddle.append("		</activity>\n");
							strMiddle.append("		<activity\n");
							strMiddle.append("			android:name=\"app.jb.generated." + activityResourceName.getClassName(c, Operation.FIND, dictionary) + "\"\n");
							strMiddle.append("			android:label=\"@string/app_name\" >\n");
							strMiddle.append("		</activity>\n");
							strMiddle.append("		<activity\n");
							strMiddle.append("			android:name=\"app.jb.generated." + activityResourceName.getClassName(c, Operation.LIST, dictionary) + "\"\n");
							strMiddle.append("			android:label=\"@string/app_name\" >\n");
							strMiddle.append("		</activity>\n");

							for(AttributeDescription ad : c.getAttributesForInterface()) {
								if (ad.isCollection()) {
									if (dictionary.containsClassWithKey(ad.getCollectionType())) {
										strMiddle.append("		<activity\n");
										strMiddle.append("			android:name=\"app.jb.generated." + activityResourceName.getClassName(c, ad, Operation.LIST, dictionary) + "\"\n");
										strMiddle.append("			android:label=\"@string/app_name\" >\n");
										strMiddle.append("		</activity>\n");
									}
								}
							}
						}
					}

					while(!line.contains("application")) {
						line = br.readLine();
					}

					while(line != null) {
						//messager.printMessage(Kind.WARNING, line, element);
						strAfter.append(line + "\n");
						line = br.readLine();
					}
				}
				else {
					String line = br.readLine();
					while(!line.contains("</manifest>")) {
						strBefore.append(line + "\n");
						line = br.readLine();
					}

					strMiddle.append("	<application\n");
					strMiddle.append("		android:allowBackup=\"true\"\n");
					strMiddle.append("		android:icon=\"@drawable/ic_launcher\"\n");
					strMiddle.append("		android:label=\"@string/app_name\"\n");
					strMiddle.append("		android:theme=\"@style/AppTheme\" >\n");
					strMiddle.append("\n");
					strMiddle.append("		<activity\n");
					strMiddle.append("			android:name=\"app.jb.generated.ProjectListActivity\"\n");
					strMiddle.append("			android:label=\"@string/app_name\" >\n");
					strMiddle.append("			<intent-filter>\n");
					strMiddle.append("				<action android:name=\"android.intent.action.MAIN\" />\n");
					strMiddle.append("\n");
					strMiddle.append("				<category android:name=\"android.intent.category.LAUNCHER\" />\n");
					strMiddle.append("			</intent-filter>\n");
					strMiddle.append("		</activity>\n");

					strMiddle.append("\n");

					if(dictionary.getTotalClasses() > 0) {
						for(ClassDescription c : dictionary.getClassValues()) {
							strMiddle.append("		<activity\n");
							strMiddle.append("			android:name=\"app.jb.generated." + activityResourceName.getClassName(c, Operation.INSERT, dictionary) + "\"\n");
							strMiddle.append("			android:label=\"@string/app_name\" >\n");
							strMiddle.append("		</activity>\n");
							strMiddle.append("		<activity\n");
							strMiddle.append("			android:name=\"app.jb.generated." + activityResourceName.getClassName(c, Operation.DETAIL, dictionary) + "\"\n");
							strMiddle.append("			android:label=\"@string/app_name\" >\n");
							strMiddle.append("		</activity>\n");
							strMiddle.append("		<activity\n");
							strMiddle.append("			android:name=\"app.jb.generated." + activityResourceName.getClassName(c, Operation.EDIT, dictionary) + "\"\n");
							strMiddle.append("			android:label=\"@string/app_name\" >\n");
							strMiddle.append("		</activity>\n");
							strMiddle.append("		<activity\n");
							strMiddle.append("			android:name=\"app.jb.generated." + activityResourceName.getClassName(c, Operation.FIND, dictionary) + "\"\n");
							strMiddle.append("			android:label=\"@string/app_name\" >\n");
							strMiddle.append("		</activity>\n");
							strMiddle.append("		<activity\n");
							strMiddle.append("			android:name=\"app.jb.generated." + activityResourceName.getClassName(c, Operation.LIST, dictionary) + "\"\n");
							strMiddle.append("			android:label=\"@string/app_name\" >\n");
							strMiddle.append("		</activity>\n");

							for(AttributeDescription ad : c.getAttributesForInterface()) {
								if (ad.isCollection()) {
									if (dictionary.containsClassWithKey(ad.getCollectionType())) {
										strMiddle.append("		<activity\n");
										strMiddle.append("			android:name=\"app.jb.generated." + activityResourceName.getClassName(c, ad, Operation.LIST, dictionary) + "\"\n");
										strMiddle.append("			android:label=\"@string/app_name\" >\n");
										strMiddle.append("		</activity>\n");
									}
								}
							}
						}
					}
					strMiddle.append("	</application>\n");

					while(line != null) {
						strAfter.append(line + "\n");
						line = br.readLine();
					}
				}

				br.close();
			}

			str.append(strBefore.toString());
			str.append(strMiddle.toString());
			str.append(strAfter.toString());

		} catch (FilerException e2) {
			//e2.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void generate() {
		if(str == null || str.length() <= 0) return;
		
		FileObject fileOut = null;
		try {
			fileOut = filer.createResource(StandardLocation.SOURCE_OUTPUT, "", "AndroidManifest.xml", element);

			BufferedWriter bw = new BufferedWriter(fileOut.openWriter());
			bw.append(str.toString());
			bw.close();
			//file.openWriter().append(str.toString()).close();
		} catch (FilerException e2) {
			e2.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
