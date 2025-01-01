import request from '@/utils/request'

// 查询旅游路线列表
export function listAddRoute(query) {
  return request({
    url: '/system/add_route/list',
    method: 'get',
    params: query
  })
}

// 查询旅游路线详细信息
export function getAddRoute(id) {
  return request({
    url: '/system/add_route/' + id,
    method: 'get'
  })
}

// 新增旅游路线
export function addAddRoute(data) {
  return request({
    url: '/system/add_route',
    method: 'post',
    data: data
  })
}

// 修改旅游路线
export function updateAddRoute(data) {
  return request({
    url: '/system/add_route',
    method: 'put',
    data: data
  })
}

// 删除旅游路线
export function delAddRoute(id) {
  return request({
    url: '/system/add_route/' + id,
    method: 'delete'
  })
}

// 生成旅游路线
export function generateRoute(data) {
  return request({
    url: '/system/add_route/generate',
    method: 'post',
    data: data
  })
} 