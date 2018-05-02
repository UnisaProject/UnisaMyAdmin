import { DescriptionPipe } from './description.pipe';


describe('DescriptionPipe', () => {

  let pipe: DescriptionPipe;

  beforeEach(() => {
    pipe = new DescriptionPipe();
  });

  it('create an instance', () => {
    expect(pipe).toBeTruthy();
  });

  it('find description for known language', () => {

  })
});
