const initLanguagesMock = (mock) => {
  mock
    .onGet('/languages')
    .reply(() => {
      const languages = [
        {
          id: '1',
          language: 'language1',
        },
        {
          id: '2',
          language: 'language2',
        },
      ];
      return [200, languages];
    });
}


export {initLanguagesMock};