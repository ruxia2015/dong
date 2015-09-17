package tool;

import common.annotation.SQLAnnotation;
import common.annotation.TableRule;
import common.util.AnnotationUtil;
import common.util.StringTools;

import java.lang.reflect.Field;


public class SimpleSQLXMLTemplate<T> {


    public static String generatorInsertSQL(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        StringBuffer sqlMapper = new StringBuffer();
        StringBuffer sb = new StringBuffer();

        StringBuffer columns = new StringBuffer();
        StringBuffer xmlFields = new StringBuffer();
        StringBuffer xmlSQL = new StringBuffer();


        for (Field key : fields) {
            String fieldName = key.getName();
            TableRule columnRuleAnnon = (TableRule) clazz.getAnnotation(TableRule.class);
            SQLAnnotation annotation = (SQLAnnotation) AnnotationUtil.getFieldAnnotation(clazz, fieldName, SQLAnnotation.class);
            if (annotation != null && annotation.is_Ingore()) {
                continue;
            }
            columns.append("," + GetNamesByBeanClassTools.getColumnNameByfield(fieldName, columnRuleAnnon));
            xmlFields.append(",#{" + fieldName + "}");
        }


        if (StringTools.isEmptyOrNone(columns.toString())) {
            return null;
        } else {
            xmlSQL.append("INSERT INTO ").append(GetNamesByBeanClassTools.getTableName(clazz))
                    .append("(" + columns.substring(1) + ")")
                    .append("values")
                    .append("(" + xmlFields.substring(1) + ")");
        }

        return xmlSQL.toString();
    }

    public static String generatorQuerySQL(Class clazz) {
        {
            Field[] fields = clazz.getDeclaredFields();
            StringBuffer sqlMapper = new StringBuffer();
            StringBuffer sb = new StringBuffer();

            StringBuffer columns = new StringBuffer();
            StringBuffer where = new StringBuffer();
            StringBuffer xmlSQL = new StringBuffer();

            for (Field key : fields) {
                String fieldName = key.getName();
                TableRule columnRuleAnnon = (TableRule) clazz.getAnnotation(TableRule.class);
                SQLAnnotation annotation = (SQLAnnotation) AnnotationUtil.getFieldAnnotation(clazz, fieldName, SQLAnnotation.class);
                if (annotation != null && annotation.is_Ingore()) {
                    continue;
                }
                String columnName = GetNamesByBeanClassTools.getColumnNameByfield(fieldName, columnRuleAnnon);
                columns.append("," + columnName);

                where.append("    <if test=\"" + fieldName + " !=null and " + fieldName + " !='' \">  \r\n")
                        .append("        AND " + columnName + " = #{" + fieldName + "}  \r\n")
                        .append("    </if> \r\n");
            }

            if (StringTools.isEmptyOrNone(where.toString())) {
                return null;
            } else {
                xmlSQL.append("SELECT " + columns.substring(1) + " FROM ").append(GetNamesByBeanClassTools.getTableName(clazz))
                        .append("  WHERE 1 = 1 \r\n")
                        .append(where.toString());
            }

            return xmlSQL.toString();
        }
    }

    public static String generatorCountSQL(Class clazz) {
        {
            Field[] fields = clazz.getDeclaredFields();
            StringBuffer sqlMapper = new StringBuffer();
            StringBuffer sb = new StringBuffer();


            StringBuffer where = new StringBuffer();
            StringBuffer xmlSQL = new StringBuffer();

            for (Field key : fields) {
                String fieldName = key.getName();
                TableRule columnRuleAnnon = (TableRule) clazz.getAnnotation(TableRule.class);
                SQLAnnotation annotation = (SQLAnnotation) AnnotationUtil.getFieldAnnotation(clazz, fieldName, SQLAnnotation.class);
                if (annotation != null && annotation.is_Ingore()) {
                    continue;
                }
                String columnName = GetNamesByBeanClassTools.getColumnNameByfield(fieldName, columnRuleAnnon);


                where.append("    <if test=\"" + fieldName + " !=null and " + fieldName + " !='' \">  \r\n")
                        .append("        AND " + columnName + " = #{" + fieldName + "}  \r\n")
                        .append("    </if> \r\n");
            }

            if (StringTools.isEmptyOrNone(where.toString())) {
                return null;
            } else {
                xmlSQL.append("SELECT COUNT(1) FROM ").append(GetNamesByBeanClassTools.getTableName(clazz))
                        .append("  WHERE 1 = 1 \r\n")
                        .append(where.toString());
            }

            return xmlSQL.toString();
        }
    }


    public static String generatorDeleteSQL(Class clazz) {
        {
            Field[] fields = clazz.getDeclaredFields();
            StringBuffer sqlMapper = new StringBuffer();
            StringBuffer sb = new StringBuffer();


            StringBuffer where = new StringBuffer();
            StringBuffer xmlSQL = new StringBuffer();

            for (Field key : fields) {
                String fieldName = key.getName();
                TableRule columnRuleAnnon = (TableRule) clazz.getAnnotation(TableRule.class);
                SQLAnnotation annotation = (SQLAnnotation) AnnotationUtil.getFieldAnnotation(clazz, fieldName, SQLAnnotation.class);
                if (annotation != null && annotation.is_Ingore()) {
                    continue;
                }
                String columnName = GetNamesByBeanClassTools.getColumnNameByfield(fieldName, columnRuleAnnon);


                where.append("    <if test=\"" + fieldName + " !=null and " + fieldName + " !='' \">  \r\n")
                        .append("        AND " + columnName + " = #{" + fieldName + "}  \r\n")
                        .append("    </if> \r\n");
            }

            if (StringTools.isEmptyOrNone(where.toString())) {
                return null;
            } else {
                xmlSQL.append("DELETE FROM  ").append(GetNamesByBeanClassTools.getTableName(clazz))
                        .append("  WHERE 1 = 1 \r\n")
                        .append(where.toString());
            }

            return xmlSQL.toString();
        }
    }


    public static String generatorUpdateSQL(Class clazz) {
        {
            Field[] fields = clazz.getDeclaredFields();
            StringBuffer sqlMapper = new StringBuffer();
            StringBuffer sb = new StringBuffer();


            StringBuffer updateColumns = new StringBuffer();
            StringBuffer where = new StringBuffer();
            StringBuffer xmlSQL = new StringBuffer();

            for (Field key : fields) {
                String fieldName = key.getName();
                TableRule columnRuleAnnon = (TableRule) clazz.getAnnotation(TableRule.class);
                SQLAnnotation annotation = (SQLAnnotation) AnnotationUtil.getFieldAnnotation(clazz, fieldName, SQLAnnotation.class);


                String columnName = GetNamesByBeanClassTools.getColumnNameByfield(fieldName, columnRuleAnnon);

                if (StringTools.isEmptyOrNone(updateColumns.toString())) {
                    updateColumns.append(columnName + " = " + columnName + "\r\n");
                }


                if (annotation == null) {
                    where.append("    <if test=\"" + fieldName + " !=null and " + fieldName + " !='' \">  \r\n")
                            .append("        AND " + columnName + " = #{" + fieldName + "}  \r\n")
                            .append("    </if> \r\n");
                } else if (annotation.is_Ingore()) {
                    continue;
                } else if (annotation.is_column()) {
                    if (!annotation.update_is_where()) {
                        // 作为更新的字段
                        updateColumns.append("    <if test=\"" + fieldName + " !=null and " + fieldName + " !='' \">  \r\n")
                                .append("         , " + columnName + " = #{" + fieldName + "}  \r\n")
                                .append("    </if> \r\n");
                    } else {
                        where.append("    <if test=\"" + fieldName + " !=null and " + fieldName + " !='' \">  \r\n")
                                .append("        AND " + columnName + " = #{" + fieldName + "}  \r\n")
                                .append("    </if> \r\n");
                    }

                }
            }
            xmlSQL.append("UPDATE   ").append(GetNamesByBeanClassTools.getTableName(clazz))
                    .append("  SET ")
                    .append(updateColumns.toString())
                    .append("    WHERE 1 = 1 \r\n")
                    .append(where.toString());

            return xmlSQL.toString();
        }
    }

    private static String getWhere(String val, SQLAnnotation annotation, String col) {
        if (annotation == null) {
            if (StringTools.isEmptyOrNone(val)) {
                return " AND " + col + "IS NULL ";
            } else {
                return " AND " + col + "= " + val;
            }
        }
        if (annotation.is_Ingore()) {
            return "";
        }
        if (!StringTools.isEmptyOrNone(annotation.where_column())) {
            col = annotation.where_column();
        }
        String tempVal = val;
        if (StringTools.isEmptyOrNone(val)) {
            return " AND " + col + "IS NULL ";
        } else if ("like".equalsIgnoreCase(annotation.where_oper())) {
            tempVal = "'%" + val + "%'";
        } else if ("in".equalsIgnoreCase(annotation.where_oper())) {
            String[] vals = val.split(",");
            String val2 = "";
            for (String str : vals) {
                val2 = val2 + ",'" + str + "'";
            }
            val2 = val2.replaceFirst(",", "");
            tempVal = " (" + val2 + ") ";
        } else {
            tempVal = "'" + tempVal + "'";
        }
        return " AND  " + col + " " + annotation.where_oper() + tempVal;
    }

/*
    public String generatorDeleteSQL(String tableName, T t, Class clazz) {

		Map<String, Object> map = getObjectValue(t, clazz);
		StringBuffer sb = new StringBuffer();
		String where = "";
		for (String key : map.keySet()) {
			String val = (String) map.get(key);
			if (!StringTools.isEmptyOrNone(val)) {
				val = "'" + val + "'";
				where = where + " AND " + key + " = " + val;
			}
		}

		if (StringTools.isEmptyOrNone(where)) {
			return null;
		} else {
			sb.append("delete FROM  " + tableName);
			sb.append(" WHERE 1 = 1 " + where);
		}
		System.out.println("delete SQL ==> " + sb.toString());
		return sb.toString();

	}

	public String generatorCountSQL(String tableName, T t, Class clazz) {
        Map<String, Object> map = getObjectValue(t, clazz);
        Map<String, Field> fMap = getFieldMap(clazz);

        StringBuffer sb = new StringBuffer();
        String where = "";
        String columns = "";
        for (String key : map.keySet()) {
            Field f = fMap.get(key);
            f.setAccessible(true);
            SQLAnnotation annotation = (SQLAnnotation) f
                    .getAnnotation(SQLAnnotation.class);

            columns = columns + key + ",";
            String val = (String) map.get(key);
            if (!StringTools.isEmptyOrNone(val)) {
                where = where + getWhere(val, annotation, key);
            }
        }

        if (StringTools.isEmptyOrNone(columns)) {
            return null;
        } else {
            columns = columns.substring(0, columns.length() - 1);
            sb.append("select count(1) as cnt from " + tableName);
            sb.append(" WHERE 1 = 1 " + where);
        }

        System.out.println("生成的count语句 ==> " + sb.toString());
        return sb.toString();
    }
	

	*/
/**
 * 生成更新语句 <功能详细描述>
 *
 * @param tableName
 * @param t
 * @param clazz
 * @return [参数说明]
 *
 * @return String [返回类型说明]
 * @exception throws [违例类型] [违例说明]
 * @see [类、类#方法、类#成员]
 *//*

	public String generatorUpdateSQL(String tableName, T t, Class clazz) {
		Map<String, Object> map = getObjectValue(t, clazz);
		StringBuffer sb = new StringBuffer();
		String where = "";
		String updateColumns = "";
		for (String key : map.keySet()) {
			String val = (String) map.get(key);
			if (StringTools.isEmptyOrNone(val)) {
				continue;
			}

			SQLAnnotation annotation = (SQLAnnotation)getFieldAnnotation(clazz,key,SQLAnnotation.class);
			
			if(annotation==null){
				updateColumns = updateColumns + key + " ='" + val + "',";
			}else  if(annotation.is_Ingore()){
				continue;
			}
			else if (annotation.is_column() && !annotation.update_is_where()) {
				// 作为更新的字段
				updateColumns = updateColumns + key + " ='" + val + "',";
			} else {
				String col = key;
				where = where + getWhere(val, annotation, col);
			}

		}

		if (StringTools.isEmptyOrNone(where)
				|| StringTools.isEmptyOrNone(updateColumns)) {
			return null;
		} else {
			updateColumns = updateColumns.substring(0,
					updateColumns.length() - 1);
			sb.append("update " + tableName);
			sb.append(" set " + updateColumns);
			sb.append(" WHERE 1 = 1 " + where);
		}
		System.out.println("update SQL ==> " + sb.toString());
		return sb.toString();

	}


	
	


	public Map<String, Field> getFieldMap(Class clazz) {
		Map<String, Field> value = new HashMap<String, Field>();

		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			value.put(field.getName(), field);
		}

		return value;
	}
*/


}
