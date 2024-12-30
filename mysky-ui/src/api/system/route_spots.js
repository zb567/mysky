import request from '@/utils/request'

// 查询旅游路线中间景点列表
export function listRoute_spots(query) {
  return request({
    url: '/system/route_spots/list',
    method: 'get',
    params: query
  })
}

// 查询旅游路线中间景点详细
export function getRoute_spots(id) {
  return request({
    url: '/system/route_spots/' + id,
    method: 'get'
  })
}

// 新增旅游路线中间景点
export function addRoute_spots(data) {
  return request({
    url: '/system/route_spots',
    method: 'post',
    data: data
  })
}

// 修改旅游路线中间景点
export function updateRoute_spots(data) {
  return request({
    url: '/system/route_spots',
    method: 'put',
    data: data
  })
}

// 删除旅游路线中间景点
export function delRoute_spots(id) {
  return request({
    url: '/system/route_spots/' + id,
    method: 'delete'
  })
}
