/*
 *************************************************************************
 * The contents of this file are subject to the Openbravo  Public  License
 * Version  1.1  (the  "License"),  being   the  Mozilla   Public  License
 * Version 1.1  with a permitted attribution clause; you may not  use this
 * file except in compliance with the License. You  may  obtain  a copy of
 * the License at http://www.openbravo.com/legal/license.html 
 * Software distributed under the License  is  distributed  on  an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific  language  governing  rights  and  limitations
 * under the License. 
 * The Original Code is Openbravo ERP. 
 * The Initial Developer of the Original Code is Openbravo SLU 
 * All portions are Copyright (C) 2008-2015 Openbravo SLU 
 * All Rights Reserved. 
 * Contributor(s):  ______________________________________.
 ************************************************************************
 */

package org.openbravo.base.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jakarta.persistence.UniqueConstraint;

/**
 * Models the business object type. The Entity is the main concept in the
 * in-memory model. An entity corresponds to a {@link Table} in the database. An
 * Entity has properties which are primitive typed, references or lists of child
 * entities.
 * 
 * @see Property
 * @see ModelProvider
 * 
 * @author iperdomo
 * @author mtaal
 */

public class Entity {

	private List<UniqueConstraint> uniqueConstraints = new ArrayList<UniqueConstraint>();

	private List<Property> properties;
	private Map<String, Property> propertiesByName;
	private Map<String, Property> propertiesByColumnName;
	private List<Property> idProperties;
	private List<Property> identifierProperties;
	private List<Property> parentProperties;
	private List<Property> orderByProperties;
	private List<Property> computedColumnProperties;

	private String name = null;
	private String tableName;
	private String tableId;
	private Class<?> mappingClass = null;
	private boolean mappingClassComputed = false;
	private String className;

	private boolean isInActive;

	private boolean isTraceable;
	private boolean isTraceableSet = false;
	private boolean isActiveEnabled;
	private boolean isOrganizationEnabled;
	// some views have this:
	private boolean isOrganizationPartOfKey;
	private boolean isClientEnabled;
	private boolean isMutable;
	private boolean isDeletable;
	private boolean isView;
	private boolean isDataSourceBased;
	private boolean isHQLBased;
	private boolean isVirtualEntity = false;

	private boolean hasInheritedFrom;

	private String treeType;
	public static final String COMPUTED_COLUMNS_PROXY_PROPERTY = "_computedColumns";
	public static final String COMPUTED_COLUMNS_CLASS_APPENDIX = "_ComputedColumns";

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	/**
	 * Initializes the entity from a table, also creates the properties from the
	 * list of Columns of the table.
	 * 
	 * @param table
	 *            the table used to initialize the Entity
	 */

	/**
	 * Checks if entity has any computed column
	 */
	public boolean hasComputedColumns() {
		return computedColumnProperties != null && computedColumnProperties.size() > 0;
	}

	public List<Property> getComputedColumnProperties() {
		return computedColumnProperties;
	}

	/**
	 * Virtual entities are used when the main entity has computed columns. These
	 * virtual entities are mapped to the same database table the main entity is
	 * mapped to, they contain all the computed column properties, making in this
	 * way possible to lazily compute them.
	 */
	public boolean isVirtualEntity() {
		return isVirtualEntity;
	}

	/**
	 * Add a property to the internal arrays of properties (common, identifier,
	 * etc.)
	 * 
	 * @param property
	 *            the Property to add
	 */
	public void addProperty(Property property) {
		getProperties().add(property);
		property.setIndexInEntity(getProperties().size() - 1);

		if (property.getColumnName() != null) {
			propertiesByColumnName.put(property.getColumnName().toLowerCase(), property);
		}
		if (property.isIdentifier()) {
			// In case of id properties that are part of a foreign key, the property was
			// already set as
			// identifier. Remove it before adding current one.
			Property duplicatedIdentifier = null;
			for ( Property i : identifierProperties) {
				if (i.getColumnName().equalsIgnoreCase(property.getColumnName())) {
					duplicatedIdentifier = i;
					break;
				}
			}
			if (duplicatedIdentifier != null) {
				identifierProperties.remove(duplicatedIdentifier);
			}
			getIdentifierProperties().add(property);
		}
		if (property.isId()) {
			getIdProperties().add(property);
		}
	}

	public List<UniqueConstraint> getUniqueConstraints() {
		return uniqueConstraints;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		// repair the name if it contains any illegal character
		this.name = removeIllegalChars(name);
	}

	private String removeIllegalChars(String fromName) {
		final char[] chars = fromName.toCharArray();
		final StringBuilder newName = new StringBuilder();
		boolean nameChanged = false;
		for (char c : chars) {
			boolean hasIllegalChar = false;
			for (char illegalChar : NamingUtil.ILLEGAL_ENTITY_NAME_CHARS) {
				if (c == illegalChar) {
					hasIllegalChar = true;
					break;
				}
			}
			if (hasIllegalChar) {
				nameChanged = true;
				continue;
			}
			newName.append(c);
		}
		return newName.toString();
	}

	public String getClassName() {
		return className;
	}

	protected void setClassName(String className) {
		this.className = className;
	}

	public void setTraceable(boolean isTraceable) {
		this.isTraceable = isTraceable;
	}

	public void setActiveEnabled(boolean isActiveEnabled) {
		this.isActiveEnabled = isActiveEnabled;
	}

	public void setOrganizationEnabled(boolean isOrganizationEnabled) {
		this.isOrganizationEnabled = isOrganizationEnabled;
	}

	public void setClientEnabled(boolean isClientEnabled) {
		this.isClientEnabled = isClientEnabled;
	}

	public void setHasInheritedFrom(boolean hasInheritedFrom) {
		this.hasInheritedFrom = hasInheritedFrom;
	}

	/**
	 * Returns the list of interfaces implemented by instances of this Entity. It is
	 * used by the entity code generation to determine which interfaces to add to
	 * the class definition.
	 * 
	 * @return comma delimited list of interfaces
	 */
	public String getImplementsStatement() {

		// NOTE not using the direct reference to the class for the interface
		// names
		// to prevent binary dependency
		final StringBuilder sb = new StringBuilder();
		if (isTraceable()) {
			sb.append("Traceable");
		}
		if (isClientEnabled()) {
			if (sb.length() > 0) {
				sb.append(", ");
			}
			sb.append("ClientEnabled");
		}
		if (isOrganizationEnabled()) {
			if (sb.length() > 0) {
				sb.append(", ");
			}
			sb.append("OrganizationEnabled");
		}
		if (isActiveEnabled()) {
			if (sb.length() > 0) {
				sb.append(", ");
			}
			sb.append("ActiveEnabled");
		}
		if (isInheritedAccessEnabled()) {
			if (sb.length() > 0) {
				sb.append(", ");
			}
			sb.append("InheritedAccessEnabled");
		}
		if (sb.length() == 0) {
			return "";
		}
		return "implements " + sb.toString();
	}

	/**
	 * Checks if the class has a certain property by name.
	 * 
	 * @param propertyName
	 *            the name used to search for the property
	 * @return returns true if there is a property with this name, false otherwise
	 */
	// TODO: it is saver to also check for the type!
	public boolean hasProperty(String propertyName) {
		return propertiesByName.get(propertyName) != null;
	}

	public void addPropertyByName(Property p) {
		propertiesByName.put(p.getName(), p);
	}

	/**
	 * Retrieves the property using the propertyName. Throws a CheckException if no
	 * property exists with that name.
	 * 
	 * @param propertyName
	 *            the name used to search for the property.
	 * @return the found property
	 * @throws CheckException
	 */
	public Property getProperty(String propertyName) {
		return getProperty(propertyName, true);
	}

	/**
	 * Retrieves the property using the propertyName. Throws a CheckException if no
	 * property exists with that name in case checkIsNotNull is true.
	 * 
	 * @param propertyName
	 *            the name used to search for the property.
	 * @param checkIsNotNull
	 *            if true, fails if property does not exists in entity, if false,
	 *            returns null in this case
	 * @return the found property
	 * @throws CheckException
	 */
	public Property getProperty(String propertyName, boolean checkIsNotNull) {
		final Property prop = propertiesByName.get( propertyName);

		return prop;
	}

	/**
	 * Retrieves the property using the columnName. Throws a CheckException if no
	 * property exists with that columnName.
	 * 
	 * @param columnName
	 *            the name used to search for the property.
	 * @return the found property
	 * @throws CheckException
	 */
	public Property getPropertyByColumnName(String columnName) {
		final Property prop = propertiesByColumnName.get( columnName.toLowerCase());
		return prop;
	}

	/**
	 * Retrieves the property using the columnName. Throws a CheckException if no
	 * property exists with that columnName in case checkIsNotNull is true.
	 * 
	 * @param columnName
	 *            the name used to search for the property.
	 * @param checkIsNotNull
	 *            if true, fails if property does not exists in entity, if false,
	 *            returns null in this case
	 * @return the found property
	 * @throws CheckException
	 */
	public Property getPropertyByColumnName(String columnName, boolean checkIsNotNull) {
		final Property prop = propertiesByColumnName.get( columnName.toLowerCase());

		return prop;
	}

	public String getPackageName() {
		final int lastIndexOf = getClassName().lastIndexOf('.');
		return getClassName().substring(0, lastIndexOf);
	}

	/**
	 * Returns the last part of the Class name of the class of this Entity. The last
	 * part is the part after the last dot.
	 * 
	 * @return the last segment of the fully qualified Class name
	 */
	public String getSimpleClassName() {
		final int lastIndexOf = getClassName().lastIndexOf('.');
		return getClassName().substring(1 + lastIndexOf);
	}

	/**
	 * An Entity is traceable if it has auditInfo fields such as created, createdBy
	 * etc.
	 * 
	 * @return true if this Entity has created, createdBy etc. properties.
	 */
	public boolean isTraceable() {
		if (!isTraceableSet) {
			int cnt = 0;
			for ( Property p : getProperties()) {
				if (p.isAuditInfo()) {
					cnt++;
				}
			}
			isTraceable = cnt == 4;
			isTraceableSet = true;
		}
		return isTraceable;
	}

	/**
	 * @return true if this Entity has an isActive property.
	 */
	public boolean isActiveEnabled() {
		return isActiveEnabled;
	}

	/**
	 * @return true if this Entity has an organization property.
	 */
	public boolean isOrganizationEnabled() {
		return isOrganizationEnabled;
	}

	/**
	 * @return true if this Entity has an inheritedFrom property.
	 */
	public boolean isInheritedAccessEnabled() {
		return hasInheritedFrom;
	}

	/**
	 * @return true if this Entity has a client property.
	 */
	public boolean isClientEnabled() {
		return isClientEnabled;
	}

	public List<Property> getProperties() {
		return properties;
	}

	/**
	 * Gets a List of properties in the entity excluding, if present, the computed
	 * column proxy virtual property. If <code>includeComputed</code> parameter is
	 * <code>true</code>, all computed columns are also excluded.
	 * 
	 * @param includeComputed
	 *            should properties for computed columns be excluded from the list
	 * @return all the properties excluding proxy and, optionally, computed columns
	 */
	public List<Property> getRealProperties(boolean includeComputed) {
		List<Property> result = new ArrayList<Property>();
		for ( Property p : properties) {
			if ((includeComputed || !p.isComputedColumn())
					&& !Entity.COMPUTED_COLUMNS_PROXY_PROPERTY.equals( p.getName())) {
				result.add(p);
			}
		}
		return result;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	/**
	 * @return the properties which make up the identifying string of this Entity
	 */
	public List<Property> getIdentifierProperties() {
		return identifierProperties;
	}

	public void setIdentifierProperties(List<Property> identifierProperties) {
		this.identifierProperties = identifierProperties;
	}

	/**
	 * Only applies if this Entity is a child of another Entity, for example this is
	 * the OrderLine of an Order.
	 * 
	 * @return the list of properties pointing to the parent, an emptylist if there
	 *         is no such association to a parent
	 */
	public List<Property> getParentProperties() {
		return parentProperties;
	}

	public void setParentProperties(List<Property> parentProperties) {
		this.parentProperties = parentProperties;
	}

	/**
	 * The orderBy properties are used when this Entity is a child of another
	 * Entity. For example if this Entity is the OrderLine with a lineNo property
	 * which determines the order of the lines. Then the lineNo property will be
	 * returned as element in the list of this method.
	 * 
	 * @return the properties which can be used to order instances of this Entity
	 */
	public List<Property> getOrderByProperties() {
		return this.orderByProperties;
	}

	public void setOrderByProperties(List<Property> orderByProperties) {
		this.orderByProperties = orderByProperties;
	}

	/**
	 * Returns the properties which make up the primary key of this Entity.
	 * 
	 * @return the list of primary key properties
	 */
	public List<Property> getIdProperties() {
		return idProperties;
	}

	public void setIdProperties(List<Property> idProperties) {
		this.idProperties = idProperties;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public String toString() {
		return getName();
	}

	public boolean isMutable() {
		return isMutable;
	}

	public void setMutable(boolean isMutable) {
		this.isMutable = isMutable;
	}

	public boolean isDeletable() {
		return isDeletable;
	}

	public void setDeletable(boolean isDeletable) {
		this.isDeletable = isDeletable;
	}

	public boolean hasCompositeId() {
		return getIdProperties().size() == 1 && getIdProperties().get(0).isCompositeId();
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public boolean isOrganizationPartOfKey() {
		return isOrganizationPartOfKey;
	}

	public void setOrganizationPartOfKey(boolean isOrganizationPartOfKey) {
		this.isOrganizationPartOfKey = isOrganizationPartOfKey;
	}

	public boolean isInActive() {
		return isInActive;
	}

	public void setInActive(boolean isInActive) {
		this.isInActive = isInActive;
	}

	public boolean isView() {
		return isView;
	}

	public void setView(boolean isView) {
		this.isView = isView;
	}

	public boolean isDataSourceBased() {
		return isDataSourceBased;
	}

	public void setDataSourceBased(boolean isDataSourceBased) {
		this.isDataSourceBased = isDataSourceBased;
	}

	public boolean isHQLBased() {
		return isHQLBased;
	}

	public void setHQLBased(boolean isHQLBased) {
		this.isHQLBased = isHQLBased;
	}

	List<String> getJavaImportsInternal() {
		return getJavaImportsInternal(properties);
	}

	private List<String> getJavaImportsInternal(List<Property> propertyList) {
		List<String> imports = new ArrayList<String>();
		Set<String> simpleImports = new HashSet<String>();
		imports.add("org.openbravo.base.structure.BaseOBObject");
		simpleImports.add("BaseOBObject");

		if (isTraceable()) {
			imports.add("org.openbravo.base.structure.Traceable");
			simpleImports.add("Traceable");
		}
		if (isClientEnabled()) {
			imports.add("org.openbravo.base.structure.ClientEnabled");
			simpleImports.add("ClientEnabled");
		}
		if (isOrganizationEnabled()) {
			imports.add("org.openbravo.base.structure.OrganizationEnabled");
			simpleImports.add("OrganizationEnabled");
		}
		if (isActiveEnabled()) {
			imports.add("org.openbravo.base.structure.ActiveEnabled");
			simpleImports.add("ActiveEnabled");
		}
		if (isInheritedAccessEnabled()) {
			imports.add("org.openbravo.base.structure.InheritedAccessEnabled");
			simpleImports.add("InheritedAccessEnabled");
		}

		// collect types of properties
		for ( Property p : propertyList) {
			String fullType, simpleType;
			if (p.isOneToMany()) {
				// add list-type here to take precedence over model class named List
				fullType = "java.util.List";
				simpleType = "List";
				if (!simpleImports.contains(simpleType)) {
					if (!simpleType.equals(getSimpleClassName())) {
						if (!imports.contains(fullType)) {
							imports.add(fullType);
							simpleImports.add(simpleType);
						}
					}
				}
			}
			if (p.getTargetEntity() != null) {
				fullType = p.getTargetEntity().getClassName();
				simpleType = p.getTargetEntity().getSimpleClassName();
			} else {
				continue;
			}
			if (!simpleImports.contains(simpleType)) {
				if (!simpleType.equals(getSimpleClassName())) {
					if (!imports.contains(fullType)) {
						imports.add(fullType);
						simpleImports.add(simpleType);
					}
				}
			}
		}

		Collections.sort(imports);
		return imports;

	}

	private static String getPackageFromClassname(String className) {
		int i = className.lastIndexOf(".");
		return className.substring(0, i);
	}

	private static String getFirstPartOfPackage(String packageName) {
		int i = packageName.indexOf(".");
		return packageName.substring(0, i);
	}

	/**
	 * Used to generate java import statements during generate.entities
	 */
	public List<String> getJavaImports() {
		return getJavaImports(properties);
	}

	public List<String> getJavaImports(List<Property> propertyList) {
		List<String> imports = getJavaImportsInternal(propertyList);
		List<String> result = new ArrayList<String>();
		String lastImport = "";
		for (String i : imports) {
			String packageName = getPackageFromClassname(i);
			if (!packageName.equals("java.lang") && !packageName.equals(getPackageName())) {
				if (!getFirstPartOfPackage(packageName).equals(lastImport)) {
					result.add("");
				}
				result.add("import " + i + ";");

				lastImport = getFirstPartOfPackage(packageName);
			}
		}
		return result;
	}
}
