import request from '@/utils/request'

// 查询存储旅游者路线的基本信息列表
export function listRoute(query) {
  return request({
    url: '/system/route/list',
    method: 'get',
    params: query
  })
}

// 查询存储旅游者路线的基本信息详细
export function getRoute(id) {
  return request({
    url: '/system/route/' + id,
    method: 'get'
  })
}

// 新增存储旅游者路线的基本信息
export function addRoute(data) {
  return request({
    url: '/system/route',
    method: 'post',
    data: data
  })
}

// 修改存储旅游者路线的基本信息
export function updateRoute(data) {
  return request({
    url: '/system/route',
    method: 'put',
    data: data
  })
}

// 删除存储旅游者路线的基本信息
export function delRoute(id) {
  return request({
    url: '/system/route/' + id,
    method: 'delete'
  })
}
