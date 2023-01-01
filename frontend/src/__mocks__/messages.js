const initMessagesMock = (mock) => {
  mock
    .onGet('/messages/')
    .reply(() => {
      const messages = [
        {
          id: '1',
          original_message: null,
          language: {
            id: '1',
            language: 'language1'
          },
          content: 'message1',
          tags: [
            {
              id: '1',
              tag: 'tag1'
            }
          ],
        }
      ];
      return [200, messages];
    });
}


export {initMessagesMock};