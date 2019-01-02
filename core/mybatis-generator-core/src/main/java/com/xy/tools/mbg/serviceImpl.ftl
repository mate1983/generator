package ${rootPackage}.service;

import ${modelPackage}.${modelName};
import ${clientPackage}.${clientName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ${modelName}ServiceImpl implements ${modelName}Service{
    @Autowired
    private  ${clientName} ${littleCamelClientName};

    @Override
    public int deleteByPrimaryKey(Long id){
        return ${littleCamelClientName}.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(${modelName} ${littleCamelModelName}){
        return ${littleCamelClientName}.insert(${littleCamelModelName});
    }
    @Override
    public int insertSelective(${modelName} ${littleCamelModelName}){
        return ${littleCamelClientName}.insertSelective(${littleCamelModelName});
    }

    @Override
    public UserDeviceHistory selectByPrimaryKey(Long id){
         return ${littleCamelClientName}.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(${modelName} ${littleCamelModelName}){
         return ${littleCamelClientName}.updateByPrimaryKeySelective(${littleCamelModelName});
    }

    @Override
    public int updateByPrimaryKey(UserDeviceHistory ${littleCamelModelName}){
        return ${littleCamelClientName}.updateByPrimaryKey(${littleCamelModelName});
    }
}