import MockAdapter from 'axios-mock-adapter'

import {initTagsMock} from './tags'
import {initMessagesMock} from './messages'
import {initLanguagesMock} from './languages'

const initMocks = (instance) => {
  const mockAdapter = new MockAdapter(instance, { delayResponse: 200 })

  initTagsMock(mockAdapter)
  initMessagesMock(mockAdapter)
  initLanguagesMock(mockAdapter)

  return mockAdapter
}

export {
  initMocks
}