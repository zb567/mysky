import request from '@/utils/request'

// 调用AI聊天接口
export function chatWithAI(data) {
  return request({
    url: '/tool/ai/simpleChat',
    method: 'post',
    data: data
  })
} 