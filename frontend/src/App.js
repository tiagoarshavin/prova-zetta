import React, { useState } from 'react';
import './App.css';
import { Layout, Row, Col, Menu, Icon, Breadcrumb, Button, Table, Radio } from 'antd';
import { PlusOutlined } from '@ant-design/icons';
import Title from 'antd/lib/typography/Title';
import SubMenu from 'antd/lib/menu/SubMenu';


const { Header, Footer, Sider, Content } = Layout;
function App() {
	const dataSource = [];

	const columns = [
		{
			title: 'Nome',
			dataIndex: 'nome',
			key: 'nome',
		},
	];

	return (
		<div className="App">
			<Layout>
				<Header style={{ padding: 10 }}>
					<Title style={{ color: 'white' }} level={3}>Desafio Prático</Title>
				</Header>
				<Layout>
					<Sider>
						<Menu
							defaultSelectedKeys={['Cargo']}
							mode="inline"
						>
							<Menu.Item key='Cargo'>
								Cargo
                           </Menu.Item>

							<Menu.Item key='Perfil'>
								Perfil
                           </Menu.Item>

							<Menu.Item key='Usuario'>
								Usuário
                           </Menu.Item>
						</Menu>
					</Sider>
					<Content>
						<Content style={{ padding: '0 25px' }}>
							<Breadcrumb style={{ margin: '16px 0' }}>
								<Breadcrumb.Item>Cargo</Breadcrumb.Item>
							</Breadcrumb>
							<div style={{ background: '#fff', padding: 24, minHeight: 580 }}>
								<Row>
									<Col offset={21}>
										<Button
											icon={<PlusOutlined />}
											type="primary"
											size="middle"
										>
											Criar Cargo
								</Button>
									</Col>
								</Row>

								<br />
								<Table
									dataSource={dataSource}
									columns={columns}
								/>
							</div>
						</Content>
					</Content>
				</Layout>
				<Footer style={{ textAlign: 'center' }}>Zetta © 2021 </Footer>
			</Layout>
		</div>
	);
}

export default App;