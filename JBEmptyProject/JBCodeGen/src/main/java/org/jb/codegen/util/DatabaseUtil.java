package org.jb.codegen.util;

public class DatabaseUtil {
	/*
	public static String getDeclareType(DescriptionDictionary dictionary, RuntimeAbstractTypedDescription info) {
		if(info.getType().equals("long") || info.getType().equals("java.lang.Long") ||
				info.getType().equals("int") || info.getType().equals("java.lang.Integer")) {
			return "INTEGER";
		}
		else if(info.getType().equals("float") || info.getType().equals("java.lang.Float")) {
			return "REAL";
		}
		else if(info.getType().equals("double") || info.getType().equals("java.lang.Double")) {
			return "REAL";
		}
		else if(info.getType().equals("boolean") || info.getType().equals("java.lang.Boolean")) {
			return "TEXT";
		}
		else if(info.getType().equals("java.lang.String")) {
			return "TEXT";
		}
		else if(info.getType().equals("java.util.Date")) {
			return "DATETIME";
		}
		else if(dictionary.containsClassWithKey(info.getType()) ||
				dictionary.containsEnumWithKey(info.getType())) {
			return "INTEGER";
		}
		else {
			return "";
		}
	}
	
	public static String getCreateTableStatement(DescriptionDictionary dictionary, RuntimeClassDescription info) {
		StringBuilder str = new StringBuilder();
		str.append("\"CREATE TABLE IF NOT EXISTS " + info.getTableName() + " (");

		List<ColumnInfo> columns = new ArrayList<ColumnInfo>();
		columns.addAll(info.getIds());
		columns.addAll(info.getColumns());
		columns.addAll(info.getJoinColumns());
		
		for (int i = 0; i < columns.size(); i++) {
			ColumnInfo column = columns.get(i);
			String strField = column.getDeclareType(dictionary);
			if(strField.length() > 0) {
				if(i > 0)
					str.append(", ");
				str.append("\"");
				str.append(" + ");
				str.append("\n");
				str.append("\t\t\t\t");
				str.append("\"" + strField);	
			}
		}
		
		str.append("\"");
		str.append(" + ");
		str.append("\n");
		str.append("\t\t\t\t");
		str.append("\");\"");
		return str.toString();
	}
	
	public static String getCreateTableStatement(DescriptionDictionary dictionary, JoinTableInfo info) {
		StringBuilder str = new StringBuilder();
		str.append("\"CREATE TABLE IF NOT EXISTS " + info.getTableName() + " (");

		List<JoinColumnInfo> joins = new ArrayList<JoinColumnInfo>();
		joins.addAll(info.getJoinColumns());
		joins.addAll(info.getInverseJoinColumns());
		
		for (int i = 0; i < joins.size(); i++) {
			JoinColumnInfo f = joins.get(i);
			String strField = f.getDeclareType(dictionary);
			if(strField.length() > 0) {
				if(i > 0)
					str.append(", ");
				str.append("\"");
				str.append(" + ");
				str.append("\n");
				str.append("\t\t\t\t");
				str.append("\"" + strField);	
			}
		}
		
		str.append("\"");
		str.append(" + ");
		str.append("\n");
		str.append("\t\t\t\t");
		str.append("\");\"");
		return str.toString();
	}
	
	public static String getDropTableStatement(DescriptionDictionary dictionary,
			TableInfo table) {
		return "\"DROP TABLE IF EXISTS " + table.getTableName() + "\"";
	}

	public static String getFindAllStatement(DescriptionDictionary dictionary,
			TableInfo table) {
		
		if(dictionary.containsEnumWithKey(table.getClassFullName())) {
			return "\"SELECT * FROM " + table.getTableName() + "\"";
		}
		
		String tables = "";		
		StringBuilder str = new StringBuilder();
		
		ClassInfo subClasse = dictionary.getClassInfo(table.getClassFullName());
		TableInfo subTable = table;
		IdInfo subId = subTable.getIds().get(0);
		
		tables += table.getTableName() + ".*";
		
		while(!subClasse.getSuperClass().equals(JBEntity.class.getName())) {
			ClassInfo superClasse = dictionary.getClassInfo(subClasse.getSuperClass());
			TableInfo superTable = dictionary.getTableInfo(superClasse.getFullName());
			IdInfo superId = superTable.getIds().get(0);
			
			str.append(" + ");
			str.append("\n");
			str.append("\t\t\t\t");
			str.append("\" JOIN " + superTable.getTableName() + " ON " + 
					superTable.getTableName() + "." + superId.getColumn() + " = " + 
					subTable.getTableName() + "." + subId.getColumn() + "\"");
			
			subClasse = superClasse;
			subTable = superTable;
			subId = superId;
			
			if(tables.length() > 0)
				tables += ", ";
			tables += superTable.getTableName() + ".*";
		}
		
		str.insert(0, "\"SELECT " + tables + " FROM " + table.getTableName() + "\"");
		
		return str.toString();
	}
	
	public static String getDropTableStatement(DescriptionDictionary dictionary,
			JoinTableInfo jt) {
		return "\"DROP TABLE IF EXISTS " + jt.getTableName() + "\"";
	}

	public static String getFindByIdStatement(DescriptionDictionary dictionary,
			TableInfo table) {
		
		if(dictionary.containsEnumWithKey(table.getClassFullName())) {
			return "\"SELECT * FROM " + table.getTableName() + " WHERE id  = \" + id";
		}
		
		String tables = "";
		StringBuilder str = new StringBuilder();
		
		ClassInfo subClasse = dictionary.getClassInfo(table.getClassFullName());
		TableInfo subTable = table;
		IdInfo subId = subTable.getIds().get(0);

		tables += table.getTableName() + ".*";
		
		while(!subClasse.getSuperClass().equals(JBEntity.class.getName())) {
			ClassInfo superClasse = dictionary.getClassInfo(subClasse.getSuperClass());
			TableInfo superTable = dictionary.getTableInfo(superClasse.getFullName());
			IdInfo superId = superTable.getIds().get(0);
			
			str.append(" + ");
			str.append("\n");
			str.append("\t\t\t\t");
			str.append("\" JOIN " + superTable.getTableName() + " ON " + 
					superTable.getTableName() + "." + superId.getColumn() + " = " + 
					subTable.getTableName() + "." + subId.getColumn() + "\"");
			
			subClasse = superClasse;
			subTable = superTable;
			subId = superId;
			
			if(tables.length() > 0)
				tables += ", ";
			tables += superTable.getTableName() + ".*";
		}
		
		ClassInfo classe = dictionary.getClassInfo(table.getClassFullName());
		if(classe != null) {
			if (!table.getIds().isEmpty()) {
				IdInfo iInfo = table.getIds().get(0);
				if(iInfo != null) {
					str.append(" + ");
					str.append("\n");
					str.append("\t\t\t\t");
					str.append("\" WHERE ");
					str.append(table.getTableName() + "." + iInfo.getColumn() + " = \" + id");
				}
			}
		}
		
		str.insert(0, "\"SELECT " + tables + " FROM " + table.getTableName() + "\"");

		return str.toString();
	}

	public static String getFindByIdsStatement(DescriptionDictionary dictionary,
											  TableInfo table) {

		if(dictionary.containsEnumWithKey(table.getClassFullName())) {
			return "\"SELECT * FROM " + table.getTableName() + " WHERE id IN \"";
		}

		String tables = "";
		StringBuilder str = new StringBuilder();

		ClassInfo subClasse = dictionary.getClassInfo(table.getClassFullName());
		TableInfo subTable = table;
		IdInfo subId = subTable.getIds().get(0);

		tables += table.getTableName() + ".*";

		while(!subClasse.getSuperClass().equals(JBEntity.class.getName())) {
			ClassInfo superClasse = dictionary.getClassInfo(subClasse.getSuperClass());
			TableInfo superTable = dictionary.getTableInfo(superClasse.getFullName());
			IdInfo superId = superTable.getIds().get(0);

			str.append(" + ");
			str.append("\n");
			str.append("\t\t\t\t");
			str.append("\" JOIN " + superTable.getTableName() + " ON " +
					superTable.getTableName() + "." + superId.getColumn() + " = " +
					subTable.getTableName() + "." + subId.getColumn() + "\"");

			subClasse = superClasse;
			subTable = superTable;
			subId = superId;

			if(tables.length() > 0)
				tables += ", ";
			tables += superTable.getTableName() + ".*";
		}

		ClassInfo classe = dictionary.getClassInfo(table.getClassFullName());
		if(classe != null) {
			if (!table.getIds().isEmpty()) {
				IdInfo iInfo = table.getIds().get(0);
				if(iInfo != null) {
					str.append(" + ");
					str.append("\n");
					str.append("\t\t\t\t");
					str.append("\" WHERE ");
					str.append(table.getTableName() + "." + iInfo.getColumn() + " IN \"");
				}
			}
		}

		str.insert(0, "\"SELECT " + tables + " FROM " + table.getTableName() + "\"");

		return str.toString();
	}
	
	public static String getFindByWhereStatement(DescriptionDictionary dictionary,
			TableInfo table, String where) {
		String tables = "";		
		StringBuilder str = new StringBuilder();
		
		ClassInfo subClasse = dictionary.getClassInfo(table.getClassFullName());
		TableInfo subTable = table;
		IdInfo subId = subTable.getIds().get(0);

		tables += table.getTableName() + ".*";
		
		while(!subClasse.getSuperClass().equals(JBEntity.class.getName())) {
			ClassInfo superClasse = dictionary.getClassInfo(subClasse.getSuperClass());
			TableInfo superTable = dictionary.getTableInfo(superClasse.getFullName());
			IdInfo superId = superTable.getIds().get(0);
			
			str.append(" + ");
			str.append("\n");
			str.append("\t\t\t\t");
			str.append("\" JOIN " + superTable.getTableName() + " ON " + 
					superTable.getTableName() + "." + superId.getColumn() + " = " + 
					subTable.getTableName() + "." + subId.getColumn() + "\"");
			
			subClasse = superClasse;
			subTable = superTable;
			subId = superId;
			
			if(tables.length() > 0)
				tables += ", ";
			tables += superTable.getTableName() + ".*";
		}
		
		if(where != null && where.length() > 0) {
			str.append(" + ");
			str.append("\n");
			str.append("\t\t\t\t");
			str.append("\" WHERE ");
			str.append(where + "\"");
		}
		
		str.insert(0, "\"SELECT " + tables + " FROM " + table.getTableName() + "\"");

		return str.toString();
	}
	
	public static String getCountStatement(DescriptionDictionary dictionary,
			TableInfo table) {
		String tables = "";		
		StringBuilder str = new StringBuilder();
		
		ClassInfo subClasse = dictionary.getClassInfo(table.getClassFullName());
		TableInfo subTable = table;
		IdInfo subId = subTable.getIds().get(0);
		
		tables += table.getTableName() + ".*";
		
		while(!subClasse.getSuperClass().equals(JBEntity.class.getName())) {
			ClassInfo superClasse = dictionary.getClassInfo(subClasse.getSuperClass());
			TableInfo superTable = dictionary.getTableInfo(superClasse.getFullName());
			IdInfo superId = superTable.getIds().get(0);
			
			str.append(" + ");
			str.append("\n");
			str.append("\t\t\t\t");
			str.append("\" JOIN " + superTable.getTableName() + " ON " + 
					superTable.getTableName() + "." + superId.getColumn() + " = " + 
					subTable.getTableName() + "." + subId.getColumn() + "\"");
			
			subClasse = superClasse;
			subTable = superTable;
			subId = superId;
			
			if(tables.length() > 0)
				tables += ", ";
			tables += superTable.getTableName() + ".*";
		}
		
		str.insert(0, "\"SELECT COUNT(*) FROM " + table.getTableName() + "\"");
		
		return str.toString();
	}
	
	public static String getValueDatabaseToObject(AttributeInfo attr, ColumnInfo f, DescriptionDictionary dictionary) {
		return getValueDatabaseToObject(attr.getType(), f.getColumn(), dictionary);
	}

	public static String getValueDatabaseToObject(String attrString, String columnString, DescriptionDictionary dictionary) {
		StringBuilder str = new StringBuilder();
		str.append("cursor.getColumnIndex(\"" + columnString + "\")");

		if(attrString.equals("int") || attrString.equals("java.lang.Integer")) {
			str.insert(0, "cursor.getInt(");
			str.append(")");
		}
		else if(attrString.equals("float") || attrString.equals("java.lang.Float")) {
			str.insert(0, "cursor.getFloat(");
			str.append(")");
		}
		else if(attrString.equals("double") || attrString.equals("java.lang.Double")) {
			str.insert(0, "cursor.getDouble(");
			str.append(")");
		}
		else if(attrString.equals("boolean") || attrString.equals("java.lang.Boolean")) {
			str.insert(0, "cursor.getString(");
			str.append(")");

			if(attrString.equals("boolean")) {
				str.insert(0, "Boolean.getBoolean(");
				str.append(")");
			}
			if(attrString.equals("java.lang.Boolean")) {
				str.insert(0, "Boolean.valueOf(");
				str.append(")");
			}
		}
		else if(attrString.equals("java.lang.String")) {
			str.insert(0, "cursor.getString(");
			str.append(")");
		}
		else if(attrString.equals("java.util.Date")) {
			str.insert(0, "cursor.getString(");
			str.append(")");

			str.insert(0, "DateUtil.toDate(");
			str.append(", \"yyyy-mm-dd\")");
		}
		else if(dictionary.containsClassWithKey(attrString)) {
			//Do nothing
			str.insert(0, "cursor.getInt(");
			str.append(")");
		}
		else if(dictionary.containsEnumWithKey(attrString)) {
			//Do nothing
			str.insert(0, "cursor.getInt(");
			str.append(")");
		}
		else {
			//Do nothing
		}

		return str.toString();
	}
	
	public static String getValueObjectToDatabase(AttributeInfo attr, DescriptionDictionary dictionary) {
		StringBuilder str = new StringBuilder();
		str.append("obj." + attr.getGetMethod() + "()");
		
		if(attr.getType().equals("int") || attr.getType().equals("java.lang.Integer")) {
			//Do nothing
		}
		else if(attr.getType().equals("float") || attr.getType().equals("java.lang.Float")) {
			//Do nothing
		}
		else if(attr.getType().equals("double") || attr.getType().equals("java.lang.Double")) {
			//Do nothing
		}
		else if(attr.getType().equals("boolean") || attr.getType().equals("java.lang.Boolean")) {
			if(attr.getType().equals("boolean")) {
				str.insert(0, "Boolean.toString(");
				str.append(")");
			}	
			if(attr.getType().equals("java.lang.Boolean")) {
				str.append(".toString()");
			}
		}
		else if(attr.getType().equals("java.lang.String")) {
			//Do nothing
		}
		else if(attr.getType().equals("java.util.Date")) {
			TemporalType type = TemporalType.TIMESTAMP;
			
			ClassInfo c = attr.getClasse();
			if(c != null) {
				TableInfo t = dictionary.getTableInfo(c.getFullName());
				if(t != null) {
					TemporalInfo tInfo = t.getTemporal(attr.getName());
					if(tInfo != null) {
						type = tInfo.getTemporalType();
					}
				}
			}
			str.insert(0, "DateUtil.fromDate(");
			str.append(", \"" + DateUtil.stringDatabaseFormat(type) + "\")");
		}
		else if(dictionary.containsClassWithKey(attr.getType())) {
			//Do nothing
			TableInfo table = dictionary.getTableInfo(attr.getType());
			ClassInfo classe = dictionary.getClassInfo(attr.getType());
			
			if(!table.getIds().isEmpty()) {
				IdInfo iInfo = table.getIds().get(0);
				AttributeInfo aInfo = classe.getAttribute(iInfo.getName());
				if(aInfo != null) {
					str.append("." + aInfo.getGetMethod() + "()");
				}
			}
		}
		else if(dictionary.containsEnumWithKey(attr.getType())) {
			str.append(".ordinal()");
		}
		else {
			//Do nothing
		}
		
		return str.toString();
	}
	*/
}
