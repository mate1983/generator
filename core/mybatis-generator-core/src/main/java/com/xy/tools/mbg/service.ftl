package ${rootPackage}.service;

import ${modelPackage}.${modelName};

public interface ${modelName}Service {
    int deleteByPrimaryKey(Long id);

    int insert(${modelName} ${littleCamelModelName});

    int insertSelective(${modelName} ${littleCamelModelName});

    ${modelName} selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(${modelName} ${littleCamelModelName});

    int updateByPrimaryKey(${modelName} ${littleCamelModelName});
}