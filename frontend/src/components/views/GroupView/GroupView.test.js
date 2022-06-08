import Adapter from "enzyme-adapter-react-16";
import { shallow, configure } from "enzyme";
import GroupView from "./GroupView";

configure({ adapter: new Adapter() });

describe("GroupView", () => {
    test("renders corectly", () => {
        shallow(<GroupView />);
    });

    test("state", () => {
        // expect(view.state("isChecked")).toBeFalsy();
    });
});
