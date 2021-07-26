/**
 *    Copyright 2009-2020 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 关于类型转换
 * 类型转换是实现 ORM 的重要一环，由于数据库中的数据类型与 Java 语言 的数据类型并不对等，所以在 PrepareStatement
 * 为 sql 语句 绑定参数时，需要从 Java 类型 转换成 JDBC 类型，而从结果集获取数据时，又要将 JDBC 类型 转换成 Java
 * 类型，Mybatis 使用 TypeHandler 完成了上述的双向转换。
 *
 * TypeHandler 是 Mybatis 中所有类型转换器的顶层接口，主要用于实现数据从 Java 类型 到 JdbcType 类型 的相互转换。
 *
 * 重要：TypeHandler 主要用于单个参数的类型转换，如果要将多个列的值转换成一个 Java 对象，可以在映射文件中定义合适的
 * 映射规则 <resultMap> 完成映射
 * @author Clinton Begin
 */
public interface TypeHandler<T> {

  /** 为 PreparedStatement类型的SQL语句绑定参数时，将数据从 Java类型 转换为 JDBC类型 */
  void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException;

  /**
   * Gets the result.
   *从结果集获取数据时，将数据由 JDBC类型 转换成 Java类型
   *
   * @param rs
   *          the rs
   * @param columnName
   *          Colunm name, when configuration <code>useColumnLabel</code> is <code>false</code>
   * @return the result
   * @throws SQLException
   *           the SQL exception
   */
  T getResult(ResultSet rs, String columnName) throws SQLException;

  T getResult(ResultSet rs, int columnIndex) throws SQLException;

  T getResult(CallableStatement cs, int columnIndex) throws SQLException;

}
