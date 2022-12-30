const initTagsMock = (mock) => {
  mock
    .onGet('/api/tags/')
    .reply(() => {
      const tags = [
        {
          id: '1',
          tag: 'tag1',
        },
        {
          id: '2',
          tag: 'tag2',
        },
      ];
      return [200, tags];
    });
}


export {initTagsMock};