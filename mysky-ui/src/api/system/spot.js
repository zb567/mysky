import request from '@/utils/request'

// 查询旅游地点存储核心信息列表
export function listSpot(query) {
  return request({
    url: '/system/spot/list',
    method: 'get',
    params: query
  })
}

// 查询旅游地点存储核心信息详细
export function getSpot(id) {
  return request({
    url: '/system/spot/' + id,
    method: 'get'
  })
}

// 新增旅游地点存储核心信息
export function addSpot(data) {
  return request({
    url: '/system/spot',
    method: 'post',
    data: data
  })
}

// 修改旅游地点存储核心信息
export function updateSpot(data) {
  return request({
    url: '/system/spot',
    method: 'put',
    data: data
  })
}

// 删除旅游地点存储核心信息
export function delSpot(id) {
  return request({
    url: '/system/spot/' + id,
    method: 'delete'
  })
}
