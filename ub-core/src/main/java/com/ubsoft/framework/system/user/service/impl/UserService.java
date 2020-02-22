package com.ubsoft.framework.system.user.service.impl;

import com.ubsoft.framework.core.service.impl.BaseService;
import com.ubsoft.framework.system.user.entity.Code;
import com.ubsoft.framework.system.user.entity.User;
import com.ubsoft.framework.system.user.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService extends BaseService<User> implements IUserService {



}
